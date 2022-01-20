package com.example.pactjvmexample.consumer.service;

import com.example.pactjvmexample.consumer.model.StudentMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class StudentListener {

    private ObjectMapper objectMapper;

    public StudentListener() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Any message listener implementation [kafkaListener, rabbitListener...]
     *
     * @param messageString
     * @throws IOException
     */
    public void consumeMessage(String messageString) throws IOException {
        StudentMessage message = objectMapper.readValue(messageString, StudentMessage.class);
        log.info("message consumer {}", message);
        // pass message into business use case
    }
}
