package com.kalidratorma.yss.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .securityMatchers((matchers) -> matchers
                        .requestMatchers("/api/**")
                        .requestMatchers("/auth/*")
                        .requestMatchers("/oauth/**")
                        .requestMatchers("/error/**")
                        //.requestMatchers(HttpMethod.GET, "/player/*", "/player/*/files/*", "/playerAsFile/*")
                )
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(HttpMethod.GET, "/player/*", "/player/*/files/*", "/playerAsFile/*", "/auth/**").authenticated()
//                        .requestMatchers(HttpMethod.GET, "/player").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.POST, "/player", "/user/**").hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.POST, "/player/*/files").hasAnyAuthority("ADMIN", "MODERATOR")
//                        .requestMatchers(HttpMethod.DELETE).hasAuthority("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/player/*/files/*").hasAnyAuthority("ADMIN", "MODERATOR")
//                        .requestMatchers(HttpMethod.PUT, "/player").hasAnyAuthority("ADMIN", "MODERATOR")
                        .requestMatchers("/user/**").hasAuthority("ADMIN")
                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
