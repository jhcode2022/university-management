package org.example.management;

import org.example.datatype.Department;
import org.example.db.UniversityDatabaseWrapper;
import org.example.util.IdParser;

public class StudentManager implements IPersonManager {

    private int currentYear;

    public void setCurrentYear(int year) {
        this.currentYear = year;
    }

    @Override
    public IPerson createPerson(String name, Department department) {
        int nextAvailableSequence = UniversityDatabaseWrapper.getLastSequence(Student.PERSON_TYPE_STUDENT, department) + 1;
        long id = IdParser.generateId(currentYear, Student.PERSON_TYPE_STUDENT, department, nextAvailableSequence);
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
