package org.example.management;

import java.util.List;
import org.example.datatype.Department;

public interface IPersonManager {
    public IPerson createPerson(String name, Department department);

    public List<Course> getCourses(IPerson person);
}
