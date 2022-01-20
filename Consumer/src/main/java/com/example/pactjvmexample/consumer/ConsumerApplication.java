package com.example.pactjvmexample.consumer;

import com.example.pactjvmexample.consumer.model.Student;
import com.example.pactjvmexample.consumer.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class ConsumerApplication {
    @Autowired
    StudentService studentService;

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);

    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            final Student newStudent = new Student();
            newStudent.setId("1");
            newStudent.setName("Fran Testing");
            newStudent.setEmail("francisco.moreno@sngular.com");
            studentService.createStudent(newStudent);

            final List<Student> students = studentService.getStudents();
            students.toString();
            System.out.println(students.toString());
        };
    }
}
