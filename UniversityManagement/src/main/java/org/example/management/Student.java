package org.example.management;

import org.example.datatype.Department;

public class Student implements IPerson {

    private static final int PERSON_TYPE_STUDENT = 20;

    private final long id;
    private final String name;

    Student(long id, String name) {
        if (!isValidId(id)) {
            throw new IllegalArgumentException("Invalid ID");
        }
        this.id = id;
        this.name = name;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    private boolean isValidId(long id) {
        int personType = (int) (id % 1_00_000_0000L / 1_000_0000L);
        if (personType != PERSON_TYPE_STUDENT) {
            return false;
        }

        int departmentId = (int) (id % 1_000_0000L / 1_0000L);
        System.out.println("departmentId: " + departmentId);
        if (Department.getDepartment(departmentId) == null) {
            return false;
        }

        return true;
    }
}
