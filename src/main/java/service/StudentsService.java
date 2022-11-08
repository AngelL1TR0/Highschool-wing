package service;

import entity.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentsService {
    private Map<String, Student> students = new HashMap<>();

    public boolean add(Student student) {
        if(students.containsKey(student.getNif())) {
            return false;
        } else{
            students.put(student.getNif(), student);
            return true;
        }
    }

    public List<Student> list() {
        return new ArrayList<>(
                students.values()
        );
    }
}
