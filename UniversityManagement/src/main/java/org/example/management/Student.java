package org.example.management;

public class Student implements IPerson {

    private static final int PERSON_TYPE_STUDENT = 20;

    private final long id;
    private final String name;

    Student(long id, String name) {
        if (!isValidId(id)) {
            throw new IllegalArgumentException("Invalid ID");
        }
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

    private boolean isValidId(long id) {
        int personType = (int) (id % 1_00_000_0000L / 1_000_0000L);
        System.out.println("personType: " + personType);
        return personType == PERSON_TYPE_STUDENT;
    }
}
