package com.packtpub.springsecurity.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.config.annotation.web.configuration.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig
        extends ResourceServerConfigurerAdapter {

    @Value("${security.oauth2.resource.jwt.keyPair.alias}")
    private String keyPairAlias;

    @Value("${security.oauth2.resource.jwt.keyPair.storePassword}")
    private String keyStorePass;

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory(
                new ClassPathResource("keys/jwtConverterStore.jks"),
                keyStorePass.toCharArray()
        ).getKeyPair(keyPairAlias);

        converter.setKeyPair(keyPair);
        return converter;
    }

} // The End...
