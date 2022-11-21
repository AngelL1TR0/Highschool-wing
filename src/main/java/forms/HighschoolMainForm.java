package forms;


import service.StudentsService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HighschoolMainForm {

    private StudentsService studentsService = new StudentsService();
    private JPanel mainPanel;
    private JButton showStudentsButton;
    private JButton showGroupsButton;
    private JButton addStudentQuickAction;
    private JPanel menusPanel;
    private JButton addGroupQuickAction;

    public HighschoolMainForm() {
        showStudensForm();
        showStudentsButton.addActionListener(e -> {
            showStudensForm();
        });
        showGroupsButton.addActionListener(e -> {
            showGroupsForm();
        });
        addStudentQuickAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window frame = SwingUtilities.windowForComponent(mainPanel);
                // Creamos un dialog para añadir un estudiante
                JDialog dialog = new JDialog(frame, "Añadir estudiante", Dialog.ModalityType.DOCUMENT_MODAL);
                // Creamos el formulario
                CreateStudentForm createStudentForm = new CreateStudentForm(studentsService);
                // Asignamos el formulario al dialog
                dialog.setContentPane(createStudentForm.mainPanel);
                dialog.pack();
                dialog.setVisible(true);
            }
        });
    }

    private void showGroupsForm() {
        deleteCenter(mainPanel);
        CreateStudentForm form = new CreateStudentForm(studentsService);
        mainPanel.add(form.mainPanel, BorderLayout.CENTER);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    public void deleteCenter(JPanel panel) {
        BorderLayout layout = (BorderLayout) panel.getLayout();
        Component panelToDelete = layout.getLayoutComponent(BorderLayout.CENTER);
        if (panelToDelete != null) {
            panel.remove(panelToDelete);
        }
    }

    private void showStudensForm() {
        deleteCenter(mainPanel);
        ShowStudentForm form = new ShowStudentForm(studentsService);
        mainPanel.add(form.mainPanel, BorderLayout.CENTER);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        var form = new HighschoolMainForm();
        frame.setContentPane(form.mainPanel);

        frame.pack();
        frame.repaint();
        frame.revalidate();
    }

}