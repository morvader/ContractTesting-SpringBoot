package com.example.pactjvmexample.consumer.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class StudentMessage {

    @NonNull
    private String messageUuid;

    @NonNull
    private Student student;

}