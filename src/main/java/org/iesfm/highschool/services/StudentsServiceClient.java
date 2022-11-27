package org.iesfm.highschool.services;

import org.iesfm.highschool.entity.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentsServiceClient {
    private Map<String, Student> students = new HashMap<>();

    public boolean add(Student student) {
        if(students.containsKey(student.getNif())) {
            return false;
        } else{
            students.put(student.getNif(), student);
            return true;
        }
    }

    public boolean update(Student student) {
        if(students.containsKey(student.getNif())) {
            students.put(student.getNif(), student);
            return true;
        } else{
            return false;
        }
    }

    public List<Student> list() {
        return new ArrayList<>(
                students.values()
        );
    }

    public boolean deleteStudent(String nif) {
        if(students.containsKey(nif)) {
            students.remove(nif);
            return true;
        } else {
            return false;
        }
    }
}
