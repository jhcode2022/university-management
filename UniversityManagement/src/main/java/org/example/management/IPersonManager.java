package org.example.management;

import org.example.datatype.Department;

public interface IPersonManager {
    IPerson createPerson(String name, Department department);
    boolean assignCourse(IPerson person, Course course);
}
