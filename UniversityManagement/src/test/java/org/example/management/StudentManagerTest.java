package org.example.management;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.example.datatype.Department;
import org.example.db.UniversityDatabaseWrapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class StudentManagerTest {

    private static MockedStatic<UniversityDatabaseWrapper> mockedStaticUniversityDatabaseWrapper;
    private StudentManager studentManagerUnderTest;

    @BeforeEach
    void setUp() {
        mockedStaticUniversityDatabaseWrapper = mockStatic(UniversityDatabaseWrapper.class);
        studentManagerUnderTest = new StudentManager();
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
        IPerson person = studentManagerUnderTest.createPerson(name, Department.COMPUTER_SCIENCE);

        // then
        assertTrue(person instanceof Student);
        assertEquals(name, person.getName());
    }

    @Test
    void createPerson_IdIsValid() {
        // given
        String name = "John Doe";
        int testLastSequence = 1234;
        Calendar calendar = new GregorianCalendar();
        int currentYear = calendar.get(Calendar.YEAR);
        mockedStaticUniversityDatabaseWrapper.when(() -> UniversityDatabaseWrapper.savePerson(any(IPerson.class))).thenReturn(true);
        mockedStaticUniversityDatabaseWrapper.when(() -> UniversityDatabaseWrapper.getLastSequence(Student.PERSON_TYPE_STUDENT, Department.COMPUTER_SCIENCE)).thenReturn(testLastSequence);

        // when
        IPerson person = studentManagerUnderTest.createPerson(name, Department.COMPUTER_SCIENCE);

        // then
        assertTrue(person instanceof Student);
        assertEquals(name, person.getName());
        long id = person.getId();
        // check ID length
        assertEquals(0, id / 1_0000_00_000_0000L);
        assertTrue((id / 1000_00_000_0000L) > 1);
        // check year
        int year = (int) (id % 1_0000_00_000_0000L / 1_00_000_0000L);
        assertEquals(currentYear, year);
        // check personType
        int personType = (int) (id % 1_00_000_0000L / 1_000_0000L);
        assertEquals(Student.PERSON_TYPE_STUDENT, personType);
        // check departmentId
        int departmentId = (int) (id % 1_000_0000L / 1_0000L);
        assertEquals(Department.COMPUTER_SCIENCE.getId(), departmentId);
        // check sequence
        int sequence = (int) (id % 1_0000L);
        assertEquals(testLastSequence + 1, sequence);
    }

    @Test
    void createPerson_handleDbSaveFail() {
        // given
        String name = "John Doe";
        mockedStaticUniversityDatabaseWrapper.when(() -> UniversityDatabaseWrapper.savePerson(any(IPerson.class))).thenReturn(false);

        // when
        IPerson person = studentManagerUnderTest.createPerson(name, Department.COMPUTER_SCIENCE);

        // then
        assertNull(person);
    }

    @Test
    void assignCourse() {
        // given
        Student mockStudent = mock(Student.class);
        Course mockCourse = mock(Course.class);
        mockedStaticUniversityDatabaseWrapper.when(() -> UniversityDatabaseWrapper.assignCourse(mockStudent, mockCourse)).thenReturn(true);

        // when
        boolean result = studentManagerUnderTest.assignCourse(mockStudent, mockCourse);

        // then
        assertTrue(result);
        verify(mockStudent).assignCourse(mockCourse);
        verify(mockCourse).assignStudent(mockStudent);
    }
}
