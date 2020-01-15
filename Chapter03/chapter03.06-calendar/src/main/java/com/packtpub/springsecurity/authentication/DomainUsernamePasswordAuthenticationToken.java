package com.packtpub.springsecurity.authentication;

import com.packtpub.springsecurity.domain.CalendarUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public final class DomainUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private String domain;

    public DomainUsernamePasswordAuthenticationToken(String principal, String credentials, String domain) {
        super(principal, credentials);
        this.domain = domain;
    }

    public DomainUsernamePasswordAuthenticationToken(CalendarUser principal, String credentials, String domain, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }
}
