package com.codetracking.progresstrackingapplication.security;

import com.codetracking.progresstrackingapplication.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final String username;
    private final String password;
    private final String email;

    public UserDetailsImpl ( User user ) {
        this.username = user.getName ();
        this.password = user.getPassword ();
        this.email = user.getEmail ();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<> ( 0 );
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * 'username' is used for uniquely identifying the user
     * and email has been used here to uniquely identifying the user
     * @return String email
     */
    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
