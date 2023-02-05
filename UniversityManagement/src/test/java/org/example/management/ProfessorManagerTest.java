package org.example.management;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

import org.example.datatype.Department;
import org.example.db.UniversityDatabaseWrapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class ProfessorManagerTest {

    private static MockedStatic<UniversityDatabaseWrapper> mockedStaticUniversityDatabaseWrapper;
    private ProfessorManager professorManagerUnderTest;

    @BeforeEach
    void setUp() {
        mockedStaticUniversityDatabaseWrapper = mockStatic(UniversityDatabaseWrapper.class);
        professorManagerUnderTest = new ProfessorManager();
    }

    @AfterEach
    void tearDown() {
        mockedStaticUniversityDatabaseWrapper.close();
    }

    @Test
    void createPerson() {
        // given
        String name = "John Doe";
        mockedStaticUniversityDatabaseWrapper.when(() -> UniversityDatabaseWrapper.savePerson(any(IPerson.class))).thenReturn(true);

        // when
        IPerson person = professorManagerUnderTest.createPerson(name, Department.COMPUTER_SCIENCE);

        // then
        assertTrue(person instanceof Professor);
        assertEquals(name, person.getName());
    }

    @Test
    void createPerson_handleDbSaveFail() {
        // given
        String name = "John Doe";
        mockedStaticUniversityDatabaseWrapper.when(() -> UniversityDatabaseWrapper.savePerson(any(IPerson.class))).thenReturn(false);

        // when
        IPerson person = professorManagerUnderTest.createPerson(name, Department.COMPUTER_SCIENCE);

        // then
        assertNull(person);
    }

    @Test
    void assignCourse() {
        // given
        Professor mockProfessor = mock(Professor.class);
        Course mockCourse = mock(Course.class);
        mockedStaticUniversityDatabaseWrapper.when(() -> UniversityDatabaseWrapper.assignCourse(mockProfessor, mockCourse)).thenReturn(true);

        // when
        boolean result = professorManagerUnderTest.assignCourse(mockProfessor, mockCourse);

        // then
        assertTrue(result);
        verify(mockProfessor).assignCourse(mockCourse);
        verify(mockCourse).assignProfessor(mockProfessor);
    }
}