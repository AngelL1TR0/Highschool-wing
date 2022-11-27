package org.iesfm.highschool.forms.students;

import org.iesfm.highschool.entity.Student;
import org.iesfm.highschool.forms.StudentTableModel;
import org.iesfm.highschool.services.StudentsServiceClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ShowStudentsForm {
    public JPanel mainPanel;
    private JTable studentTable;
    private JButton addStudentButton;
    private JButton deleteButton;
    private JButton modificarButton;

    private StudentsServiceClient studentsService;

    public ShowStudentsForm(StudentsServiceClient studentsServiceClient) {
        this.studentsService = studentsServiceClient;

        StudentTableModel studentTableModel = new StudentTableModel(studentsServiceClient.list());
        this.studentTable.setModel(studentTableModel);

        addStudentButton.addActionListener(e -> {
            // Obtenemos la ventana en la que está este formulario
            Window frame = SwingUtilities.windowForComponent(mainPanel);
            // Creamos un dialog para añadir un estudiante
            JDialog dialog = new JDialog(frame, "Añadir estudiante", Dialog.ModalityType.DOCUMENT_MODAL);
            // Creamos el formulario
            CreateStudentTabbedForm createStudentForm = new CreateStudentTabbedForm(studentsServiceClient);
            // Asignamos el formulario al dialog
            dialog.setContentPane(createStudentForm.mainPanel);
            dialog.pack();
            dialog.setVisible(true);

            dialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    studentTableModel.setStudents(studentsServiceClient.list());
                    studentTableModel.fireTableDataChanged();
                }
            });
        });
        deleteButton.addActionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            String selectedNif = studentTableModel.getStudent(selectedRow).getNif();
            if (studentsService.deleteStudent(selectedNif)) {
                studentTableModel.setStudents(studentsServiceClient.list());
                studentTableModel.fireTableDataChanged();
                JOptionPane.showMessageDialog(mainPanel, "Estudiante eliminado");

            } else {
                JOptionPane.showMessageDialog(mainPanel, "No se ha podido eliminar el estudiante");
            }
        });
        modificarButton.addActionListener(e -> {
            Window frame = SwingUtilities.windowForComponent(mainPanel);

            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "No hay nada seleccionado");
            } else {
                Student student = studentTableModel.getStudent(selectedRow);

                JDialog dialog = new JDialog(frame, "Modificar estudiante", Dialog.ModalityType.DOCUMENT_MODAL);
                // Creamos el formulario
                ModifyStudentForm form = new ModifyStudentForm(studentsServiceClient, student);
                // Asignamos el formulario al dialog
                dialog.setContentPane(form.mainPanel);
                dialog.pack();
                dialog.setVisible(true);

                dialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        studentTableModel.setStudents(studentsServiceClient.list());
                        studentTableModel.fireTableDataChanged();
                    }
                });
            }

        });
    }
}
