package forms;

import entity.Student;
import service.StudentsService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateStudentForm {
    public JPanel mainPanel;
    private JTextField nifTf;
    private JTextField nameTf;
    private JTextField surnameTf;
    private JTextField zipCodeTf;
    private JButton guardarButton;

    private StudentsService studentsService;

    public CreateStudentForm(StudentsService studentsService) {
        this.studentsService = studentsService;

        guardarButton.addActionListener(
                e -> {
                    String nif = nifTf.getText();
                    String name = nameTf.getText();
                    String surname = surnameTf.getText();
                    int zipCode = Integer.parseInt(zipCodeTf.getText());
                    Student student = new Student(nif, name, surname, zipCode);

                    boolean created = studentsService.add(student);
                    if (created) {
                        Window dialog = SwingUtilities.windowForComponent(mainPanel);
                        // Cerramos el dialog después de guardar el estudiante
                        dialog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(mainPanel, "No se ha creado el estudiante, duplicado.");
                    }
                }
        );
    }
}