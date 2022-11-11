package forms;

import service.StudentsService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ShowStudentForm {

    private JPanel mainPanel;
    private JTable studentTable;
    private JButton addStudentButton;
    private JButton deleteButton;
    private JButton modifyButton;
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