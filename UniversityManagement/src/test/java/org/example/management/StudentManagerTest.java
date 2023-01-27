package org.example.management;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.example.datatype.Department;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentManagerTest {

    StudentManager studentManagerUnderTest;

    @BeforeEach
    void setUp() {
        studentManagerUnderTest = new StudentManager();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createPerson() {
        // given
        String name = "John Doe";

        // when
        IPerson person = studentManagerUnderTest.createPerson(name, Department.COMPUTER_SCIENCE);

        // then
        assertTrue(person instanceof Student);
        assertEquals(name, person.getName());
    }

    @Test
    void assignCourse() {
        // given
        Student mockStudent = mock(Student.class);
        Course mockCourse = mock(Course.class);

        // when
        boolean result = studentManagerUnderTest.assignCourse(mockStudent, mockCourse);

        // then
        assertTrue(result);
        verify(mockStudent).assignCourse(mockCourse);
        verify(mockCourse).assignStudent(mockStudent);
    }

    @Test
    void getCourses() {
    }
}
