package com.orelease.etc.config.security;


import com.orelease.etc.impl.entity.Client;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * The ETSUSerDetails is responsible to verify the user. SRP
 * <p>
 * Implements:
 * - org.springframework.security.core.userdetails.UserDetails; which is created by Spring Security
 * to give information about the user.
 * Have instance of User Model
 * </p>
 * Constructor:
 * - {@code ETSUserDetails(User user)}
 *
 * @author Mehdi Haidari
 */


public record ETSUserDetails(Client user) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }


    public boolean isExited(){
        return this.user.getExited();
    }

    public Client getUser() {
        return this.user;
    }
}
