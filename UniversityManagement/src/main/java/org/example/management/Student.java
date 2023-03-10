package org.example.management;

import java.util.ArrayList;
import java.util.List;
import org.example.datatype.Department;

public class Student implements IPerson {

    public static final int PERSON_TYPE_STUDENT = 20;

    private final long id;
    private final String name;
    private final List<Course> courses;

    Student(long id, String name) {
        if (!isValidId(id)) {
            throw new IllegalArgumentException("Invalid ID");
        }
        this.id = id;
        this.name = name;
        this.courses = new ArrayList<>();
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    void assignCourse(Course course) {
        courses.add(course);
    }

    @Override
    public List<Course> getCourses() {
        return courses;
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
