package org.example.management;

public class Student implements IPerson {

    private final long id;
    private final String name;

    Student(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
