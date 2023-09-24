package com.codetracking.progresstrackingapplication.security;

import com.codetracking.progresstrackingapplication.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfiguration {

    /**
     * 1. Create the UserDetails objects for each user
     * 2. Provide it to the UserDetailsManager of your choice
     * 3. Create rules for authentication and authorization
     */

    @Bean
    public UserDetailsService userDetailsService ( ) {
        return new UserDetailsServiceImpl ();
    }

    @Bean
    public SecurityFilterChain securityFilterChain ( HttpSecurity http ) throws Exception {
        return http.csrf ( AbstractHttpConfigurer::disable )
                        .authorizeHttpRequests ( authorizeHttpRequests -> authorizeHttpRequests
                                                                            .requestMatchers ( "/progress/welcome", "/auth/signIn", "/auth/login" )
                                                                            .permitAll () )
                        .authorizeHttpRequests ( authorizeHttpRequests -> authorizeHttpRequests
                                                                            .requestMatchers ( "/progress/**", "/topics/**", "/problems/**" )
                                                                            .authenticated () )
                   .httpBasic ( Customizer.withDefaults () )
                   .build ();
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder ();
    }

    @Bean
    public AuthenticationProvider authenticationProvider () {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService ( userDetailsService () );
        authenticationProvider.setPasswordEncoder ( passwordEncoder() );
        return authenticationProvider;
    }

}
