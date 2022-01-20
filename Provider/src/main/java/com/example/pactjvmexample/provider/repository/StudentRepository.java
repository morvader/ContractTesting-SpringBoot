package com.example.pactjvmexample.provider.repository;

import com.example.pactjvmexample.provider.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
}
