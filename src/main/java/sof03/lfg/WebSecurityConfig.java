package sof03.lfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import sof03.lfg.web.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(antMatcher("/css/**")).permitAll() // Enable css when logged out
                        .requestMatchers(antMatcher("/")).permitAll() // Allow access to index page without
                                                                      // authentication
                        .requestMatchers(antMatcher("/register")).permitAll() // Allow access to register page without
                                                                              // authentication
                        .requestMatchers(antMatcher("/events")).permitAll() // Allow access to events page without
                                                                            // authentication")))
                        .requestMatchers(toH2Console()).permitAll()
                        .anyRequest().authenticated())
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(toH2Console()))
                .headers(headers -> headers
                        .frameOptions(frameoptions -> frameoptions
                                .disable()))
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/events", true) // Redirect to events page after login
                        .permitAll())
                .logout(logout -> logout
                        .permitAll());

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
