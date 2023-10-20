package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home","/groups","/groups/{id}").permitAll()
                        .anyRequest().authenticated()
                )
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers(HttpMethod.POST).permitAll().anyRequest().authenticated()
////                        .requestMatchers("/", "/home","/api/groups","/api/groups/{id}").permitAll()
////                        .anyRequest().authenticated()
//                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                ;

        return http.build();

//        .authorizeHttpRequests((authz) -> authz
//                .anyRequest().authenticated()
//        )
//                .formLogin(form -> form
//                        .loginPage("/auth/login").permitAll()
//                        .defaultSuccessUrl("/auth/success", true))
//                .logout(form -> form
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST")).permitAll()
//                        .invalidateHttpSession(true)
//                        .clearAuthentication(true)
//                        .deleteCookies("JSESSIONID", "XSRF-TOKEN"));
//        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}