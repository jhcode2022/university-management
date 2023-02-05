package org.example.util;

import org.example.datatype.Department;

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
    public static int getIdType() {
        return 0;
    }

    public static int getYear() {
        return 0;
    }

    public static Department getDepartment() {
        return null;
    }

    public static int getSequence() {
        return 0;
    }

    public static boolean isIdValid() {
        return false;
    }
}
