package com.videoplatform.videoplatform.security;

import com.videoplatform.videoplatform.controller.AdminController;
import com.videoplatform.videoplatform.controller.ApiVideoController;
import com.videoplatform.videoplatform.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final AdminController adminController;
    private final ApiVideoController apiVideoController;
    private final UserController userController;


    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService, AdminController adminController, ApiVideoController apiVideoController, UserController userController) {
        this.customUserDetailsService = customUserDetailsService;
        this.adminController = adminController;
        this.apiVideoController = apiVideoController;
        this.userController = userController;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/user/password/reset", "/api/user/password/reset/confirm").permitAll()
                        .requestMatchers("/auth/home", "/auth/login", "/auth/signup", "/auth/forgotpassword", "/auth/reset-password", "/auth/password/resets/").permitAll()
                        .requestMatchers("/view/upload").hasRole("ADMIN")
                        .requestMatchers("/api/user/create", "/api/user/verify", "/api/user/verify/confirm").permitAll()
                        .requestMatchers("/api/videos/upload").hasRole("ADMIN")
                        .requestMatchers("/admin/").hasRole("ADMIN") // Restrict /admin/** to ROLE_ADMIN
                        .requestMatchers("/api/videos/**").hasAnyRole("ADMIN", "USER") // Restrict /api/videos/** to both roles
                        .requestMatchers( "user/video/**").authenticated()
                        .requestMatchers("/user/").hasRole("USER")
                        .requestMatchers("/admin/edit/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .successHandler((request, response, authentication) -> {
                            for (GrantedAuthority authority : authentication.getAuthorities()) {
                                if (authority.getAuthority().equals("ROLE_ADMIN")) {
                                    response.sendRedirect("/admin");
                                    return;
                                }
                            }
                            response.sendRedirect("/user"); // Assuming /user is the user dashboard page
                        })
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/auth/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}