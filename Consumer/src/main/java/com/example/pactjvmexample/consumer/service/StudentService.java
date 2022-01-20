package com.example.pactjvmexample.consumer.service;

import com.example.pactjvmexample.consumer.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StudentService {

    private static final String BASE_URI_STUDENTS = "/students/";

    private final RestTemplate restTemplate;

    @Autowired
    public StudentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Student createStudent(Student student) {
        return restTemplate.postForObject(BASE_URI_STUDENTS, student, Student.class);
    }

    public Student getStudent(String id) {
        return restTemplate.getForObject(BASE_URI_STUDENTS + id, Student.class);
    }

    public List<Student> getStudents() {
        return restTemplate.exchange(BASE_URI_STUDENTS, HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
        }).getBody();
    }

    public void updateStudent(Student student) {
        restTemplate.put(BASE_URI_STUDENTS, student, Student.class);
    }

    public void deleteStudent(String id) {
        restTemplate.delete(BASE_URI_STUDENTS + id, Student.class);
    }
}
