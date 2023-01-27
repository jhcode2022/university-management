package org.example.management;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*
Student ID format:
YEAR_PERSONTYPE_DEPARTMENT_SEQUENCE
2023_20_301_0001
 */
class StudentTest {

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
}
