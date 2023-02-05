package org.example.management;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.List;
import org.junit.jupiter.api.Test;

/*
Professor ID format:
PERSONTYPE_DEPARTMENT_SEQUENCE
10_301_00001
 */
class ProfessorTest {

    @Test
    void constructor_Valid() {
        // given
        long id = 10_301_00001L;
        String name = "Jane Smith";

        // when
        Professor professor = new Professor(id, name);

        // then
        assertNotNull(professor);
    }

    @Test
    void constructor_IncorrectPersonTypeInId() {
        // given
        long id = 20_301_00001L;
        String name = "Jane Smith";

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> new Professor(id, name));
    }

    @Test
    void constructor_InvalidDepartmentInId() {
        // given
        long id = 10_999_00001L;
        String name = "Jane Smith";

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> new Professor(id, name));
    }

    @Test
    void getId() {
        // given
        long id = 10_301_00001L;
        String name = "Jane Smith";

        // when
        Professor professor = new Professor(id, name);

        // then
        assertEquals(id, professor.getId());
    }

    @Test
    void getName() {
        // given
        long id = 10_301_00001L;
        String name = "Jane Smith";

        // when
        Professor professor = new Professor(id, name);

        // then
        assertEquals(name, professor.getName());
    }

    @Test
    void getCourses_Empty() {
        // given
        long id = 10_301_00001L;
        String name = "Jane Smith";
        Professor professor = new Professor(id, name);

        // when
        List<Course> result = professor.getCourses();

        // then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void getCourses_OneCourse() {
        // given
        long id = 10_301_00001L;
        String name = "Jane Smith";
        Professor professor = new Professor(id, name);
        Course mockCourse = mock(Course.class);
        professor.assignCourse(mockCourse);

        // when
        List<Course> result = professor.getCourses();

        // then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockCourse, result.get(0));
    }
}