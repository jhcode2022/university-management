package org.example.management;

import org.example.datatype.Department;

public class ProfessorManager implements IPersonManager {

    @Override
    public IPerson createPerson(String name, Department department) {
        return null;
    }

    @Override
    public boolean assignCourse(IPerson person, Course course) {
        return false;
    }
}
