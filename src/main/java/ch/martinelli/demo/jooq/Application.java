package ch.martinelli.demo.jooq;

import org.jooq.impl.DefaultConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jooq.DefaultConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public DefaultConfigurationCustomizer jooqConfigurationCustomizer() {
        return (DefaultConfiguration c) -> c.settings().withExecuteWithOptimisticLocking(true);
    }
}
