package com.ilimitech.webapp.realstate.administration.config;

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
                                .requestMatchers("/administrator/index").permitAll()
                                /**
                                 * Login Controller begin
                                 */
                                .requestMatchers("/login/**").permitAll()
                                .requestMatchers("/register/**").permitAll()
                                .requestMatchers("/captcha").permitAll()
                                .requestMatchers("/check-username").permitAll()
                                .requestMatchers("/check-email").permitAll()
                                /**
                                 * Login Controller end
                                 */
                                /**
                                 * Realstate security portal begin
                                 */
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/realstate/**").permitAll()
                                .requestMatchers("/property/**").permitAll()
                                .requestMatchers("/search-property").permitAll()
                                .requestMatchers("/property/contact").permitAll()
                                /**
                                 * Realstate security portal end
                                 */
                                .requestMatchers("/upload/**").permitAll()
                                .requestMatchers("/images/**").permitAll()
                                .requestMatchers("/imagesAjax/**").permitAll()

                                /**
                                 * Realstate security dashboard begin
                                 */
                                .requestMatchers("/dashboard/**").permitAll()
                                /**
                                 * Realstate security dashboard end
                                 */
                                .requestMatchers("/user/users").hasRole("ADMIN")
                                .requestMatchers("/user/edit/**", "/delete/**").hasRole("ADMIN")
                                .requestMatchers("/user/profile","/logout", "/logs").authenticated()
                                .anyRequest().authenticated()
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
//                                .successForwardUrl("/users")
                                .defaultSuccessUrl("/dashboard/", true)
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
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
