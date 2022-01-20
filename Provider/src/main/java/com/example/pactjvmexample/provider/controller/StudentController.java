package com.example.pactjvmexample.provider.controller;

import com.example.pactjvmexample.provider.exceptions.StudentNotFoundException;
import com.example.pactjvmexample.provider.model.Student;
import com.example.pactjvmexample.provider.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Student createStudent(@RequestBody Student student) {
        log.info("SAVE STUDENT {}", student);
        return studentRepository.save(student);
    }

    @GetMapping
    public List<Student> getStudents() {
        log.info("FIND ALL");
        return StreamSupport.stream(studentRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public Student getStudent(@PathVariable String id) {
        log.info("GET STUDENT BY ID {}", id);
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateStudent(@RequestBody Student student) {
        log.info("UPDATE STUDENT {}", student);
        studentRepository.save(student);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteStudent(@PathVariable String id) {
        log.info("DELETE STUDENT BY ID {}", id);
        studentRepository.delete(Student.builder().id(id).build());
    }
}
