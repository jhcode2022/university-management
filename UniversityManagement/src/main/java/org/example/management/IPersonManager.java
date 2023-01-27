package org.example.management;

import java.util.List;

public interface IPersonManager {
    public IPerson createPerson();
    public List<Course> getCourses(IPerson person);
}
