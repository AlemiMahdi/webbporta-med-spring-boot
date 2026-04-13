package com.wigell.cinema.config;

import com.wigell.cinema.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private final CustomUserDetailsService service;

    public SecurityConfig(CustomUserDetailsService service){
        this.service = service;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/api/v1/movies").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/movies/{movieId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/v1/movies").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/movies/{movieId}").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/api/v1/rooms").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/rooms/{roomId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/v1/rooms").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/rooms/{roomId}").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/api/v1/customers").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/v1/customers").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/customers/{customerId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/customers/{customerId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/v1/customers/{customerId}/addresses").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/customers/{customerId}/addresses/{addressId}").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/api/v1/screenings").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/v1/screenings").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/screenings/{screeningId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/screenings").hasAnyRole("USER", "ADMIN")

                .requestMatchers(HttpMethod.POST, "/api/v1/bookings").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/v1/bookings").hasRole("USER")
                .requestMatchers(HttpMethod.PATCH, "/api/v1/bookings/{bookingId}").hasRole("USER")

                .requestMatchers(HttpMethod.POST, "/api/v1/tickets").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/v1/tickets").hasRole("USER")

                .requestMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated()
            )
            .userDetailsService(service)
            .httpBasic(basic -> {});

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

}
