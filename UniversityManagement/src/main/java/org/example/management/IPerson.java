package org.example.management;

import java.util.List;

public interface IPerson {
    long getId();
    String getName();
    List<Course> getCourses();
}
