package org.iesfm.highschool.forms;

import org.iesfm.highschool.entity.Student;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StudentTableModel extends AbstractTableModel {

    private enum StudentTableColumns {
        // Aquí defino todas las columnas
        Nif("NIF"),
        Name("Nombre"),
        Surname("Apellidos"),
        ZipCode("Código postal");

        // Estos son los campos de cada columna
        final String header;

        StudentTableColumns(String header) {
            this.header = header;
        }
    }

    private List<Student> students;

    public StudentTableModel(List<Student> students) {
        super();
        this.students = students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return StudentTableColumns.values().length;
    }

    @Override
    public String getColumnName(int column) {
        return StudentTableColumns.values()[column].header;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student = students.get(rowIndex);
        switch (StudentTableColumns.values()[columnIndex]) {
            case Name:
                return student.getName();
            case Surname:
                return student.getSurname();
            case Nif:
                return student.getNif();
            case ZipCode:
                return student.getZipCode();
            default:
                throw new RuntimeException("No existe la columna " + columnIndex);
        }
    }

    public void add(Student student) {
        students.add(student);
    }

    public void delete(int selectedRow) {
        students.remove(selectedRow);
    }

    public Student getStudent(int index) {
        return students.get(index);
    }
}
