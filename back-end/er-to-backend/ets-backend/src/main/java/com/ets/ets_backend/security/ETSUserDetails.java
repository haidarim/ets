package com.ets.ets_backend.security;


import com.ets.ets_backend.model.User;
import lombok.Getter;
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

@Getter
public record ETSUserDetails(User user) implements UserDetails {

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
        return this.user.isExited();
    }
}
