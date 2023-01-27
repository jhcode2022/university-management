package org.example.management;

import static org.junit.jupiter.api.Assertions.*;

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
    void getCourses() {
    }
}
