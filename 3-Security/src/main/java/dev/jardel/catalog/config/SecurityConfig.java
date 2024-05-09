package dev.jardel.catalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /*
    * https://docs.spring.io/spring-security/reference/migration-7/configuration.html
    * O método filterChain define o que o Spring Security deve fazer para cada requisição HTTP
    */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/**").permitAll() // URLs que o Spring Security vai permitir para todos os usuários
                        .anyRequest()
                        //.authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login") // URL que o Spring Security vai redirecionar se o usuário não estiver autenticado
                        .permitAll()
                )
                .rememberMe(Customizer.withDefaults());

        return http.build();
    }
}
