package org.example.util;

import static org.junit.jupiter.api.Assertions.*;

import org.example.datatype.Department;
import org.example.management.Professor;
import org.example.management.Student;
import org.junit.jupiter.api.Test;

class IdParserTest {

    @Test
    void getIdType_Professor() {
        // given
        long id = 10_301_00001L;

        // when
        int personType = IdParser.getIdType(id);

        // then
        assertEquals(Professor.PERSON_TYPE_PROFESSOR, personType);
    }

    @Test
    void getIdType_Student() {
        // given
        long id = 2023_20_301_0001L;

        // when
        int personType = IdParser.getIdType(id);

        // then
        assertEquals(Student.PERSON_TYPE_STUDENT, personType);
    }

    @Test
    void getYear_Professor() {
        // given
        long id = 10_301_00001L;

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> IdParser.getYear(id));
    }

    @Test
    void getYear_Student() {
        // given
        long id = 2023_20_301_0001L;

        // when
        int year = IdParser.getYear(id);

        // then
        assertEquals(2023, year);
    }

    @Test
    void getDepartment_Professor() {
        // given
        long id = 10_301_00001L;

        // when
        Department department = IdParser.getDepartment(id);

        // then
        assertEquals(Department.COMPUTER_SCIENCE, department);
    }

    @Test
    void getDepartment_Student() {
        // given
        long id = 2023_20_301_0001L;

        // when
        Department department = IdParser.getDepartment(id);

        // then
        assertEquals(Department.COMPUTER_SCIENCE, department);
    }

    @Test
    void getSequence_Professor() {
        // given
        long id = 10_301_00001L;

        // when
        int sequence = IdParser.getSequence(id);

        // then
        assertEquals(00001, sequence);
    }

    @Test
    void getSequence_Student() {
        // given
        long id = 2023_20_301_0001L;

        // when
        int sequence = IdParser.getSequence(id);

        // then
        assertEquals(0001, sequence);
    }

    @Test
    void isIdValid() {
    }

    @Test
    void generateId_Professor() {
        // given
        int year = 2023;
        int personType = Professor.PERSON_TYPE_PROFESSOR;
        Department department = Department.COMPUTER_SCIENCE;
        int sequence = 12345;

        // when
        long id = IdParser.generateId(year, personType, department, sequence);

        // then
        assertEquals(10_301_12345L, id);
    }

    @Test
    void generateId_Student() {
        // given
        int year = 2023;
        int personType = Student.PERSON_TYPE_STUDENT;
        Department department = Department.COMPUTER_SCIENCE;
        int sequence = 1234;

        // when
        long id = IdParser.generateId(year, personType, department, sequence);

        // then
        assertEquals(2023_20_301_1234L, id);
    }
}