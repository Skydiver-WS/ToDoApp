package com.example.todoapp.config.secure;

import com.example.todoapp.security.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    @Bean
    public PasswordEncoder dbPasswordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
    @Bean
    @SneakyThrows
    public AuthenticationManager authenticationManager (HttpSecurity http,
                                                        UserDetailsService detailsService,
                                                        PasswordEncoder passwordEncoder){
        log.info("Start auth manager");

        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(detailsService);

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(passwordEncoder);
        authenticationProvider.setUserDetailsService(detailsService);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
        log.info(AuthenticationManagerBuilder.class.getName() + " is successful");
        return authenticationManagerBuilder.build();
    }

//    @Bean
//    @SneakyThrows
//    public SecurityFilterChain filterChain (HttpSecurity httpSecurity, AuthenticationManager authenticationManager){
//    httpSecurity.csrf(AbstractHttpConfigurer::disable)
//            .httpBasic(Customizer.withDefaults())
//            .authorizeHttpRequests(r -> r.requestMatchers("/swagger-ui/**",
//                    "/swagger-resources/*",
//                    "/v3/api-docs/**",
//                    "/user",
//                    "/user/sing-in",
//                    "/user/create",
//                    "/note",
//                    "/comment",
//                    "/note/findByTag/*").permitAll())
//            .sessionManagement(httpSecuritySessionManagementConfigurer ->
//                    httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            .authenticationManager(authenticationManager)
//            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//    return httpSecurity.build();
//    }

    @Bean
    @SneakyThrows
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity, AuthenticationManager authenticationManager){
        log.info("Start conf " + SecurityFilterChain.class.getName());
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationManager(authenticationManager)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        log.info("Finish conf " + SecurityFilterChain.class.getName());
        return httpSecurity.build();
    }
}
