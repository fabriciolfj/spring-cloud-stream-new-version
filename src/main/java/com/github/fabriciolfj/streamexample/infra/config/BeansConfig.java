package com.github.fabriciolfj.streamexample.infra.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.transform.Source;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Slf4j
@Configuration
public class BeansConfig {

    /*
    * function produz e consome a mensagem
    * supplier produz a mensagem
    * consumer consume a mensagem
    * */
    @Bean
    public Consumer<String> person() {
        return value -> {
            log.info("Consumindo o valor: {}", value);
        };
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
