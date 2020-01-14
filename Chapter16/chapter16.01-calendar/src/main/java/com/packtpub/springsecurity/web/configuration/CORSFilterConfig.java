package com.packtpub.springsecurity.web.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * To activate profile in bootRun:
 * <p>
 * Gradle:
 * gradle bootRun -Dspring.profiles.active=corsFilterBean
 * <p>
 * Maven
 * mvn spring-boot:run -Drun.profiles=corsFilterBean
 */
@Configuration
//@Profile({"corsFilterBean", "default"})
//@Primary
public class CORSFilterConfig {

    @Bean
    public FilterRegistrationBean corsFilterRegistration() {
        FilterRegistrationBean registrationBean =
                new FilterRegistrationBean(new CORSFilter());
        registrationBean.setName("CORS Filter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

} // The End...
