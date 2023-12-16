package com.springteam.TechProduct.securityconfig;

import com.springteam.TechProduct.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private CustomUserDetails customUserDetails;
    private JwtAuthEntryPoint authEntryPoint;


    //constructor
    @Autowired
    public SecurityConfig(CustomUserDetails customUserDetails, JwtAuthEntryPoint authEntryPoint)
    {
        this.authEntryPoint = authEntryPoint;
        this.customUserDetails = customUserDetails;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/crud/products/**").hasAnyAuthority("USER","ADMIN","MANAGER")
                        .requestMatchers(HttpMethod.DELETE,"/api/crud/products/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/crud/products/**").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/crud/products").hasAuthority("MANAGER")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(config -> config.authenticationEntryPoint(authEntryPoint))
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception{
        return auth.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTAuthenticationInterceptor jwtAuthenticationFilter(){
        return new JWTAuthenticationInterceptor();
    }
}
