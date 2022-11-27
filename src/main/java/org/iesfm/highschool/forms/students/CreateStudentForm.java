package org.iesfm.highschool.forms.students;

import org.iesfm.highschool.entity.Student;
import org.iesfm.highschool.services.StudentsServiceClient;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;

public class CreateStudentForm {
    public JPanel mainPanel;
    private JTextField nifTf;
    private JTextField nameTf;
    private JTextField surnameTf;
    private JTextField zipCodeTf;
    private JButton guardarButton;

    private StudentsServiceClient studentsServiceClient;

    public CreateStudentForm(StudentsServiceClient studentsServiceClient) {
        this.studentsServiceClient = studentsServiceClient;

        guardarButton.addActionListener(
                e -> {
                    try {
                        String nif = nifTf.getText();
                        String name = nameTf.getText();
                        String surname = surnameTf.getText();
                        int zipCode = Integer.parseInt(zipCodeTf.getText());
                        Student student = new Student(nif, name, surname, zipCode);

                        boolean created = studentsServiceClient.add(student);
                        if (created) {
                            // Si se ha creado bien el estudiante, cerramos el dialog
                            Window dialog = SwingUtilities.windowForComponent(mainPanel);
                            // Cerramos el dialog después de guardar el estudiante
                            dialog.dispose();
                        } else {
                            JOptionPane.showMessageDialog(mainPanel, "No se ha creado el estudiante, duplicado.");
                        }
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(mainPanel, "El código postal debe ser un número.");

                    }
                }
        );
    }

    private void createUIComponents() {
        NumberFormatter numberFormatter = new NumberFormatter();
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setMinimum(0);
        numberFormatter.setMaximum(Integer.MAX_VALUE);
        numberFormatter.setAllowsInvalid(false);

        // If you want the value to be committed on each keystroke instead of focus lost
        numberFormatter.setCommitsOnValidEdit(true);
        zipCodeTf = new JFormattedTextField(numberFormatter);
    }
}
