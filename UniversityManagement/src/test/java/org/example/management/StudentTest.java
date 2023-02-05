package org.example.management;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.List;
import org.junit.jupiter.api.Test;

/*
Student ID format:
YEAR_PERSONTYPE_DEPARTMENT_SEQUENCE
2023_20_301_0001
 */
class StudentTest {

    @Test
    void constructor_Valid() {
        // given
        long id = 2023_10_301_0001L;
        String name = "John Doe";

        // when
        Student student = new Student(id, name);

        // then
        assertNotNull(student);
    }

    @Test
    void constructor_IncorrectPersonTypeInId() {
        // given
        long id = 2023_10_301_0001L;
        String name = "John Doe";

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> new Student(id, name));
    }

    @Test
    void constructor_InvalidDepartmentInId() {
        // given
        long id = 2023_20_999_0001L;
        String name = "John Doe";

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> new Student(id, name));
    }

    @Test
    void getId() {
        // given
        long id = 2023_20_301_0001L;
        String name = "John Doe";

        // when
        Student student = new Student(id, name);

        // then
        assertEquals(id, student.getId());
    }

    @Test
    void getName() {
        // given
        long id = 2023_20_301_0001L;
        String name = "John Doe";

        // when
        Student student = new Student(id, name);

        // then
        assertEquals(name, student.getName());
    }

    @Test
    void getCourses_Empty() {
        // given
        long id = 2023_20_301_0001L;
        String name = "John Doe";
        Student student = new Student(id, name);

        // when
        List<Course> result = student.getCourses();

        // then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void getCourses_OneCourse() {
        // given
        long id = 2023_20_301_0001L;
        String name = "John Doe";
        Student student = new Student(id, name);
        Course mockCourse = mock(Course.class);
        student.assignCourse(mockCourse);

        // when
        List<Course> result = student.getCourses();

        // then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockCourse, result.get(0));
    }
}
