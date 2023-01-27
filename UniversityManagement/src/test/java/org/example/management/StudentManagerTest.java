package org.example.management;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

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
        mockedStaticUniversityDatabaseWrapper.when(() -> UniversityDatabaseWrapper.assignCourse(mockStudent, mockCourse)).thenReturn(true);

        // when
        boolean result = studentManagerUnderTest.assignCourse(mockStudent, mockCourse);

        // then
        assertTrue(result);
        verify(mockStudent).assignCourse(mockCourse);
        verify(mockCourse).assignStudent(mockStudent);
    }

//    @Test
//    void getCourses() {
//        // given
//        Student mockStudent = mock(Student.class);
//        Course mockCourse1 = mock(Course.class);
//        Course mockCourse2 = mock(Course.class);
//        List<Course> coursesForTesting = new ArrayList<>();
//        coursesForTesting.add(mockCourse1);
//        coursesForTesting.add(mockCourse2);
//        doReturn(coursesForTesting).when(mockStudent).getCourses();
//
//        // when
//        List<Course> courses = studentManagerUnderTest.getCourses(mockStudent);
//
//        // then
//        assertEquals(2, courses.size());
//        assertTrue(courses.contains(mockCourse1));
//        assertTrue(courses.contains(mockCourse2));
//    }
}
