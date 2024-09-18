package org.goorm.everytime.global.config;

import lombok.RequiredArgsConstructor;
import org.goorm.everytime.auth.handler.CustomLogoutSuccessHandler;
import org.goorm.everytime.auth.jwt.*;
import org.goorm.everytime.auth.service.PrincipalOauth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends Exception {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;
//    private final PrincipalOauth2UserService principalOauth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(c -> {
                            CorsConfigurationSource source = request -> {
                                var cors = new CorsConfiguration();
                                cors.addAllowedOriginPattern("*"); //명시
                                cors.setAllowedMethods(List.of("*"));
                                cors.setAllowedHeaders(List.of("*"));
                                cors.setAllowCredentials(true);
                                cors.setMaxAge(3600L);
                                return cors;
                            };
                            c.configurationSource(source);
                        }
                )
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/user/profile").authenticated()
                        .requestMatchers("/**").permitAll()
                )
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(
                        (exception) -> exception
                                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandler)
                )
                .logout((logout) -> logout
                        .logoutUrl("/user/logout")
                        .logoutSuccessHandler(customLogoutSuccessHandler)
                );
//                .oauth2Login(oauth2 -> oauth2
//                        .userInfoEndpoint(userInfo -> userInfo.userService(principalOauth2UserService))
//                );

        return http.build();
    }
}