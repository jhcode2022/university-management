package org.example.datatype;

public enum Department {

    BUSINESS_ADMINISTRATION(100),
    ECONOMICS(101),
    KOREAN_LITERATURE(200),
    ENGLISH_LITERATURE(201),
    ELECTRICAL_ENGINEERING(300),
    COMPUTER_SCIENCE(301);

    private final int id;

    Department(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Department getDepartment(int id) {
        for (Department department : Department.values()) {
            if (department.getId() == id) {
                return department;
            }
        }
        return null;
    }

}
