package org.example.management;

import java.util.List;
import org.example.datatype.Department;

public class ProfessorManager implements IPersonManager {

    @Override
    public IPerson createPerson(String name, Department department) {
        return null;
    }

    @Override
    public List<Course> getCourses(IPerson person) {
        return null;
    }
}
