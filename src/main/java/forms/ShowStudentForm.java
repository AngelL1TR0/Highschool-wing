package forms;

import entity.Student;
import service.GroupsService;
import service.StudentsService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ShowStudentForm {

    public JPanel mainPanel;
    private JTable studentTable;
    private JButton addStudentButton;
    private JButton deleteButton;
    private JButton modifyButton;
    private JPanel buttonsPanel;
    private JButton estudiantesButton;
    private JButton gruposButton;
    private StudentsService studentsService;

    public ShowStudentForm(StudentsService studentsService) {
        this.studentsService = studentsService;

        StudentTableModel studentTableModel = new StudentTableModel(studentsService.list());
        this.studentTable.setModel(studentTableModel);

        addStudentButton.addActionListener(e -> {
            // Obtenemos la ventana en la que está este formulario
            Window frame = SwingUtilities.windowForComponent(mainPanel);
            // Creamos un dialog para añadir un estudiante
            JDialog dialog = new JDialog(frame, "Añadir estudiante", Dialog.ModalityType.DOCUMENT_MODAL);
            // Creamos el formulario
            CreateStudentForm createStudentForm = new CreateStudentForm(studentsService);
            // Asignamos el formulario al dialog
            dialog.setContentPane(createStudentForm.mainPanel);
            dialog.pack();
            dialog.setVisible(true);

            dialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    studentTableModel.setStudents(studentsService.list());
                    studentTableModel.fireTableDataChanged();
                }
            });
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            String selectedNif = studentTableModel.getStudent(selectedRow).getNif();
            if (studentsService.deleteStudent(selectedNif)) {
                studentTableModel.setStudents(studentsService.list());
                studentTableModel.fireTableDataChanged();
                JOptionPane.showMessageDialog(mainPanel, "Estudiante eliminado");

            } else {
                JOptionPane.showMessageDialog(mainPanel, "No se ha podido eliminar el estudiante");
            }
        });
        modifyButton.addActionListener(e -> {
            Window frame = SwingUtilities.windowForComponent(mainPanel);

            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "No hay nada seleccionado");
            } else {
                Student student = studentTableModel.getStudent(selectedRow);

                JDialog dialog = new JDialog(frame, "Modificar estudiante", Dialog.ModalityType.DOCUMENT_MODAL);
                // Creamos el formulario
                ModifyStudentForm form = new ModifyStudentForm(studentsService, student);
                // Asignamos el formulario al dialog
                dialog.setContentPane(form.mainPanel);
                dialog.pack();
                dialog.setVisible(true);

                dialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        studentTableModel.setStudents(studentsService.list());
                        studentTableModel.fireTableDataChanged();
                    }
                });
            }
            gruposButton.addActionListener(actionEvent -> {
                if(mainPanel.isShowing()){
                    gruposButton.setBackground(Color.PINK);
                }else {
                    gruposButton.setBackground(Color.WHITE);
                }
                ShowGroupsForm groupsForm = new ShowGroupsForm(new GroupsService());
                mainPanel.add(groupsForm.mainPanel, BorderLayout.CENTER);
                mainPanel.repaint();
                mainPanel.revalidate();

            });
            estudiantesButton.addActionListener(actionEvent -> {
                if(mainPanel.isShowing()){
                    estudiantesButton.setBackground(Color.PINK);
                }else {
                    estudiantesButton.setBackground(Color.WHITE);
                }
                mainPanel.repaint();
                mainPanel.revalidate();
            });

        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        var form = new ShowStudentForm(new StudentsService());
        frame.setContentPane(form.mainPanel);

        frame.pack();
        frame.repaint();
        frame.revalidate();
    }
}