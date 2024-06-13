package api.todo_list_api.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;
import org.springframework.security.web.SecurityFilterChain;

import api.todo_list_api.security.jwt.JwtConfigurer;
import api.todo_list_api.security.jwt.JwtTokenProvider;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

        @Autowired
        private JwtTokenProvider tokenProvider;

        @Bean
        PasswordEncoder passwordEncoder() {
                Map<String, PasswordEncoder> encoders = new HashMap();
                Pbkdf2PasswordEncoder value = new Pbkdf2PasswordEncoder("", 8, 185000,
                                SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
                encoders.put("pbkdf2", value);
                DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
                passwordEncoder.setDefaultPasswordEncoderForMatches(value);
                return passwordEncoder;
        }

        @Bean
        AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http
                                .httpBasic(basic -> basic.disable())
                                .csrf(AbstractHttpConfigurer::disable)
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(
                                                authorizeHttpRequests -> authorizeHttpRequests
                                                                .requestMatchers(
                                                                                "/auth/login",
                                                                                "/auth/refresh**",
                                                                                "/auth/register**",
                                                                                "/swagger-ui/**",
                                                                                "/v3/api-docs/**")
                                                                .permitAll()
                                                                .requestMatchers("/api/**").authenticated()
                                                                .requestMatchers("/users").denyAll())
                                .cors()
                                .and()
                                .apply(new JwtConfigurer(tokenProvider))
                                .and()
                                .build();
        }
}
