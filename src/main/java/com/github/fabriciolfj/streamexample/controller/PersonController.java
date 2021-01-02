package com.github.fabriciolfj.streamexample.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.streamexample.domain.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonController {

    private final StreamBridge streamBridge;
    private final ObjectMapper objectMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPerson() throws JsonProcessingException {
        final var person = Person.builder()
                .name("Fabricio").build();

        final var json = objectMapper.writeValueAsString(person);
        final var msg = MessageBuilder.withPayload(json)
                .build();

        streamBridge.send("person-out-0", msg);
    }
}
