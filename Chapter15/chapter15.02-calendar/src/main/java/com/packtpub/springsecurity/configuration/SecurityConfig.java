package com.packtpub.springsecurity.configuration;

import com.packtpub.springsecurity.authentication.CalendarUserAuthenticationProvider;
import com.packtpub.springsecurity.service.CalendarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.header.writers.DelegatingRequestMatcherHeaderWriter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Spring Security Config Class
 *
 * @see {@link WebSecurityConfigurerAdapter}
 * @since chapter11.00
 */
@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)

// Thymeleaf needs to use the Thymeleaf configured FilterSecurityInterceptor
// and not the default Filter from AutoConfiguration.
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory
            .getLogger(SecurityConfig.class);

    @Autowired
    private CalendarUserAuthenticationProvider calendarUserAuthenticationProvider;

    /**
     * Configure AuthenticationManager.
     * <p>
     * NOTE:
     * Due to a known limitation with JavaConfig:
     * <a href="https://jira.spring.io/browse/SPR-13779">
     * https://jira.spring.io/browse/SPR-13779</a>
     * <p>
     * We cannot use the following to expose a {@link UserDetailsManager}
     * <pre>
     *     http.authorizeRequests()
     * </pre>
     * <p>
     * In order to expose {@link UserDetailsManager} as a bean, we must create  @Bean
     *
     * @param auth AuthenticationManagerBuilder
     * @throws Exception Authentication exception
     * @see {@link super.userDetailsService()}
     * @see {@link com.packtpub.springsecurity.service.DefaultCalendarService}
     */
    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(calendarUserAuthenticationProvider)
//            .userDetailsService(userDetailsService)
//            .passwordEncoder(passwordEncoder)
        ;
    }


    /**
     * BCryptPasswordEncoder password encoder
     *
     * @return
     */
    @Description("Standard PasswordEncoder")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }


    /**
     * HTTP Security configuration
     *
     * <pre><http auto-config="true"></pre> is equivalent to:
     * <pre>
     *  <http>
     *      <form-login />
     *      <http-basic />
     *      <logout />
     *  </http>
     * </pre>
     * <p>
     * Which is equivalent to the following JavaConfig:
     *
     * <pre>
     *     http.formLogin()
     *          .and().httpBasic()
     *          .and().logout();
     * </pre>
     *
     * @param http HttpSecurity configuration.
     * @throws Exception Authentication configuration exception
     * @see <a href="http://docs.spring.io/spring-security/site/migrate/current/3-to-4/html5/migrate-3-to-4-jc.html">
     * Spring Security 3 to 4 migration</a>
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // Matching
        http.authorizeRequests()
                // FIXME: TODO: Allow anyone to use H2 (NOTE: NOT FOR PRODUCTION USE EVER !!! )
                .antMatchers("/admin/h2/**").permitAll()

                .antMatchers("/").permitAll()
                .antMatchers("/login/*").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/signup/*").permitAll()
                .antMatchers("/errors/**").permitAll()
                .antMatchers("/admin/*").access("hasRole('ADMIN') and isFullyAuthenticated()")
                .antMatchers("/events/").hasRole("ADMIN")
                .antMatchers("/**").hasRole("USER");

        // Login
        http.formLogin()
                .loginPage("/login/form")
                .loginProcessingUrl("/login")
                .failureUrl("/login/form?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/default", true)
                .permitAll();

        // non-CSRF enabled Logout
        /*http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login/form?logout")
                .deleteCookies("JSESSIONID").invalidateHttpSession(true)
                .permitAll();*/

        // CSRF enabled logout fro GET requests to logout
        http.logout()
                .logoutRequestMatcher(
                        new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login/form?logout")
                .deleteCookies("JSESSIONID").invalidateHttpSession(true)
                .permitAll();

        // Anonymous
        http.anonymous();

        // CSRF is enabled by default, with Java Config
        http.csrf();

        // Exception Handling
        http.exceptionHandling()
                .accessDeniedPage("/errors/403")
        ;


        /* Chapter 16 section 02 */

        // Header Controls
        http.headers()
                .contentTypeOptions()
                .and()
                .xssProtection()
                .and()
                .cacheControl()
                .and()
                .httpStrictTransportSecurity()
                .and()

                // Allow <frameset> by disabling frameOptions() headers, in order to use H2 web console
                .frameOptions().disable();

        // Static Headers
        http.headers()
                .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy", "default-src 'self'"))
                .addHeaderWriter(new StaticHeadersWriter("X-WebKit-CSP", "default-src 'self'"));

        // Headers Writer's
        http.headers()
                .addHeaderWriter(
                        new XFrameOptionsHeaderWriter(
                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN));

        // DelegatingRequestMatcherHeaderWriter
        DelegatingRequestMatcherHeaderWriter headerWriter =
                new DelegatingRequestMatcherHeaderWriter(
                        new AntPathRequestMatcher("/login"),
                        new XFrameOptionsHeaderWriter());
        // add DelegatingRequestMatcherHeaderWriter
        http.headers()
                .addHeaderWriter(headerWriter);


    } // end configure()

    @Description("AuthenticationMnager that will generate an authentication token unlike {@link PreAuthenticatedAuthenticationProvider}")
    @Bean
    @DependsOn({"defaultCalendarService"})
    public CalendarUserAuthenticationProvider calendarUserAuthenticationProvider(
            CalendarService calendarService,
            PasswordEncoder passwordEncoder) {
        return new CalendarUserAuthenticationProvider(calendarService, passwordEncoder);
    }


    /**
     * This is the equivalent to:
     * <pre>
     *     <http pattern="/resources/**" security="none"/>
     *     <http pattern="/css/**" security="none"/>
     *     <http pattern="/webjars/**" security="none"/>
     * </pre>
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(final WebSecurity web) throws Exception {

        // Ignore static resources and webjars from Spring Security
        web.ignoring()
                .antMatchers("/resources/**")
                .antMatchers("/css/**")
                .antMatchers("/webjars/**")
        ;

        // Thymeleaf needs to use the Thymeleaf configured FilterSecurityInterceptor
        // and not the default Filter from AutoConfiguration.
        final HttpSecurity http = getHttp();
        web.postBuildAction(() -> {
            web.securityInterceptor(http.getSharedObject(FilterSecurityInterceptor.class));
        });
    }

} // The End...
