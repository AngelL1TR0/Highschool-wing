package forms;

import entity.Student;
import service.StudentsService;

import javax.swing.*;
import java.awt.*;

public class ModifyStudentForm {
    public JPanel mainPanel;
    private JTextField nifTf;
    private JTextField nameTf;
    private JTextField surnameTf;
    private JTextField zipCodeTf;
    private JButton modificarButton;

    public ModifyStudentForm(
            StudentsService studentsServiceClient,
            Student student) {
        nifTf.setText(student.getNif());
        nameTf.setText(student.getName());
        surnameTf.setText(student.getSurname());
        zipCodeTf.setText("" + student.getZipCode());
        modificarButton.addActionListener(e -> {
            Student modifiedStudent = new Student(
                    student.getNif(),
                    nameTf.getText(),
                    surnameTf.getText(),
                    Integer.parseInt(zipCodeTf.getText())
            );
            if(studentsServiceClient.update(modifiedStudent)){
                // Si se ha creado bien el estudiante, cerramos el dialog
                Window dialog = SwingUtilities.windowForComponent(mainPanel);
                // Cerramos el dialog después de guardar el estudiante
                dialog.dispose();
            } else{
                JOptionPane.showMessageDialog(mainPanel, "No se ha modificado el estudiante.");

            }
        });
    }
}

