package com.ilimitech.administration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers("/administrator/register/**").permitAll()
                                .requestMatchers("/administrator/check-username").permitAll()
                                .requestMatchers("/administrator/check-email").permitAll()
                                .requestMatchers("/administrator/index").permitAll()
                                .requestMatchers("/administrator/captcha").permitAll()
                                /**
                                 * Realstate security begin
                                 */
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/realstate/**").permitAll()
                                .requestMatchers("/property/**").permitAll()
                                .requestMatchers("/search-property").permitAll()
                                .requestMatchers("/property/contact").permitAll()
                                /**
                                 * Realstate security end
                                 */
                                .requestMatchers("/administrator/users").hasRole("ADMIN")
                                .requestMatchers("/administrator/edit/**", "/administrator/delete/**").hasRole("ADMIN")
                                .requestMatchers("/administrator/profile","/administrator/logout", "/administrator/logs").authenticated()
                                .anyRequest().authenticated()
                ).formLogin(
                        form -> form
                                .loginPage("/administrator/login")
                                .loginProcessingUrl("/administrator/login")
//                                .successForwardUrl("/users")
                                .defaultSuccessUrl("/", true)
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/administrator/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
