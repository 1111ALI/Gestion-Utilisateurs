package com.almo.gestionUtilisateur.securityConfig;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


//private final PersonDetailService userDetailService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authRequests ->
                        authRequests
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/contact").permitAll()
                                .requestMatchers("/register").permitAll()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/logout").permitAll()
                                .requestMatchers("/store/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                         //   .loginPage("/login")
                            .defaultSuccessUrl("/", true)
                           // .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutSuccessUrl("/")

                );

        return http.build();
    }
        @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




////    @Bean
////    public UserDetailsService userDetailsService(){
////        return userDetailService;
////    }
////   // @Autowired
////    public void configureGlobale(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
////    }
//
////}


}
