//package com.samah.userservice.config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//
//public class WebSecurityConfig {
//
//    private static final String[] WHITE_LIST_URLS = {
//            "/hello",
//            "/register",
//            "/verifyRegistration*",
//            "/resendVerifyToken*"
//    };
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(11);
//
//    }
//
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests().anyRequest().permitAll();
//        return http.build();
//    }
//
////    @Bean
////    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http.authorizeHttpRequests(
////                        authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry.requestMatchers("/v1/users").permitAll()
////                                .requestMatchers("/foos/**");
////                              //  .access(new WebExpressionAuthorizationManager("isAuthenticated() and hasIpAddress('11.11.11.11')")).anyRequest().authenticated())
////               // .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
////                //.csrf(AbstractHttpConfigurer::disable);
////        return http.build();
////    }
//}
////}
