package org.example.management;

import org.example.datatype.Department;
import org.example.db.UniversityDatabaseWrapper;

public class StudentManager implements IPersonManager {

    private int currentYear;

    public void setCurrentYear(int year) {
        this.currentYear = year;
    }

    @Override
    public IPerson createPerson(String name, Department department) {
        int personType = Student.PERSON_TYPE_STUDENT;
        int departmentId = department.getId();
        int nextAvailableSequence = UniversityDatabaseWrapper.getLastSequence(Student.PERSON_TYPE_STUDENT, department) + 1;
        long id = currentYear * 1_00_000_0000L + personType * 1_000_0000L + departmentId * 1_0000L + nextAvailableSequence;
        Student student = new Student(id, name);
        if (!UniversityDatabaseWrapper.savePerson(student)) {
            return null;
        }
        return student;
    }

    @Override
    public boolean assignCourse(IPerson person, Course course) {
        assert(person instanceof Student);

        boolean isCourseAssignTransactionSuccessful = UniversityDatabaseWrapper.assignCourse(person, course);
        if (!isCourseAssignTransactionSuccessful) {
            return false;
        }
        ((Student) person).assignCourse(course);
        course.assignStudent((Student) person);
        return true;
    }
}
