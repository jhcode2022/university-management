package org.example.util;

import org.example.datatype.Department;
import org.example.db.UniversityDatabaseWrapper;
import org.example.management.Professor;
import org.example.management.Student;

/*
Professor ID format:
PERSONTYPE_DEPARTMENT_SEQUENCE
10_301_00001
 */
/*
Student ID format:
YEAR_PERSONTYPE_DEPARTMENT_SEQUENCE
2023_20_301_0001
 */
public class IdParser {

    public static int getIdType(long id) {
        if (id >= 10_000_000_000L) {
            return Student.PERSON_TYPE_STUDENT;
        } else {
            return Professor.PERSON_TYPE_PROFESSOR;
        }
    }

    public static int getYear(long id) {
        if (getIdType(id) == Student.PERSON_TYPE_STUDENT) {
            int year = (int) (id % 1_0000_00_000_0000L / 1_00_000_0000L);
            return year;
        } else if (getIdType(id) == Professor.PERSON_TYPE_PROFESSOR) {
            throw new IllegalArgumentException("Professor ID format does not have year information!");
        } else {
            throw new IllegalArgumentException("Unknown ID format!");
        }
    }

    public static Department getDepartment(long id) {
        if (getIdType(id) == Student.PERSON_TYPE_STUDENT) {
            int departmentId = (int) (id % 1_000_0000L / 1_0000L);
            return Department.getDepartment(departmentId);
        } else if (getIdType(id) == Professor.PERSON_TYPE_PROFESSOR) {
            int departmentId = (int) (id % 1_000_00000L / 1_00000);
            return Department.getDepartment(departmentId);
        } else {
            throw new IllegalArgumentException("Unknown ID format!");
        }
    }

    public static int getSequence(long id) {
        if (getIdType(id) == Student.PERSON_TYPE_STUDENT) {
            int sequence = (int) (id % 1_0000L);
            return sequence;
        } else if (getIdType(id) == Professor.PERSON_TYPE_PROFESSOR) {
            int sequence = (int) (id % 1_00000L);
            return sequence;
        } else {
            throw new IllegalArgumentException("Unknown ID format!");
        }
    }

    public static boolean isIdValid(long id) {
        return false;
    }

    public static long generateId(int year, int personType, Department department, int sequence) {
        if (personType == Student.PERSON_TYPE_STUDENT) {
            return generateStudentId(year, department, sequence);
        } else if (personType == Professor.PERSON_TYPE_PROFESSOR) {
            return generateProfessorId(department, sequence);
        } else {
            throw new IllegalArgumentException("Unknown ID format!");
        }
    }

    private static long generateStudentId(int year, Department department, int sequence) {
        return year * 1_00_000_0000L + Student.PERSON_TYPE_STUDENT * 1_000_0000L + department.getId() * 1_0000L + sequence;
    }

    private static long generateProfessorId(Department department, int sequence) {
        return Professor.PERSON_TYPE_PROFESSOR * 1_000_00000L + department.getId() * 1_00000L + sequence;
    }
}
