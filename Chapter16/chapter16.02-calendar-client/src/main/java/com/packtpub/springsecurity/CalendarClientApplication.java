package com.packtpub.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication(scanBasePackages = {
        "com.packtpub.springsecurity",
        "com.packtpub.springsecurity.configuration",
        "com.packtpub.springsecurity.domain",
        "com.packtpub.springsecurity.web",
        "com.packtpub.springsecurity.web.configuration"
}
)
@EnableOAuth2Client
public class CalendarClientApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CalendarClientApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CalendarClientApplication.class);
    }

} // The End...
