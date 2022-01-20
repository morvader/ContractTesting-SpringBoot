package com.example.pactjvmexample.provider;

import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Consumer;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.PactBrokerAuth;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.spring.junit5.MockMvcTestTarget;
import com.example.pactjvmexample.provider.controller.StudentController;
import com.example.pactjvmexample.provider.model.Student;
import com.example.pactjvmexample.provider.repository.StudentRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@Provider("student-provider")
@Consumer("student-consumer-rest")
//@PactBroker(url = "http://pactbroker:9292", authentication = @PactBrokerAuth(username = "pact_workshop", password = "pact_workshop"))
@PactFolder( "./Pacts")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProviderRestContractTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentRepository studentRepository;

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void verifyPact(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @BeforeEach
    void setUp(PactVerificationContext context) {
        Mockito.reset(studentRepository);

        MockMvcTestTarget testTarget = new MockMvcTestTarget();
        testTarget.setControllers(studentController);
        context.setTarget(testTarget);
    }

    @State("Student 1 exists")
    public void student1Exists() {
        when(studentRepository.findById("1")).thenReturn(Optional.of(createFakeStudent("1")));
    }

    @State("Students exist")
    public void studentsExist() {
        when(studentRepository.findAll()).thenReturn(List.of(createFakeStudent("1"), createFakeStudent("2")));
    }

    @State("No students exist")
    public void noStudentExist() {
        when(studentRepository.findAll()).thenReturn(Collections.emptyList());
    }

    private Student createFakeStudent(String s) {
        Faker faker = new Faker();
        return Student.builder()
                .id(s)
                .name(faker.name().firstName())
                .studentNumber(faker.number().randomNumber())
                .email(faker.internet().emailAddress()).build();
    }
}
