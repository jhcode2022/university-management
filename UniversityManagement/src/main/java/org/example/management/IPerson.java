package org.example.management;

import java.util.List;

public interface IPerson {
    long getId();
    String getName();
    void assignCourse(Course course);
    List<Course> getCourses();
}
