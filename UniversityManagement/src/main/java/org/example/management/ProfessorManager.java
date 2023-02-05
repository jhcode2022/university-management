package org.example.management;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.example.datatype.Department;
import org.example.db.UniversityDatabaseWrapper;
import org.example.util.IdParser;

public class ProfessorManager implements IPersonManager {

    @Override
    public IPerson createPerson(String name, Department department) {
        int nextAvailableSequence = UniversityDatabaseWrapper.getLastSequence(Professor.PERSON_TYPE_PROFESSOR, department) + 1;
        long id = IdParser.generateId(-1, Professor.PERSON_TYPE_PROFESSOR, department, nextAvailableSequence);
        Professor professor = new Professor(id, name);
        return professor;
    }

    @Override
    public boolean assignCourse(IPerson person, Course course) {
        assert(person instanceof Professor);

        boolean isCourseAssignTransactionSuccessful = UniversityDatabaseWrapper.assignCourse(person, course);
        if (!isCourseAssignTransactionSuccessful) {
            return false;
        }
        ((Professor) person).assignCourse(course);
        course.assignProfessor((Professor) person);
        return true;
    }
}
