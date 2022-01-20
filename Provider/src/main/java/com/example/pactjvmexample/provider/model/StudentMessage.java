package com.example.pactjvmexample.provider.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentMessage {

    private String messageUuid;

    private Student student;

}