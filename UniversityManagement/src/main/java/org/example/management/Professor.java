package org.example.management;

import java.util.List;

public class Professor implements IPerson {

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void assignCourse(Course course) {

    }

    @Override
    public List<Course> getCourses() {
        return null;
    }
}
