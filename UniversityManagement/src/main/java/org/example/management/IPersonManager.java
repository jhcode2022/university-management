package org.example.management;

import java.util.List;
import org.example.datatype.Department;

public interface IPersonManager {
    IPerson createPerson(String name, Department department);
    boolean assignCourse(IPerson person, Course course);
    List<Course> getCourses(IPerson person);
}
