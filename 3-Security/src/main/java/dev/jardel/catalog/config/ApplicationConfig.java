package dev.jardel.catalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApplicationConfig {

    /* Este trecho de código define um método chamado passwordEncoder
     * que retorna uma nova instância da classe BCryptPasswordEncoder.
     * A anotação @Bean indica que o método deve ser tratado como um bean
     * e gerenciado pelo framework Spring, com isso o método passwordEncoder
     * pode ser injetado em outros lugares da aplicação.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
