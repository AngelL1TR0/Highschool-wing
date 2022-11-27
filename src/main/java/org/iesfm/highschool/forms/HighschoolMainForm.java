package org.iesfm.highschool.forms;

import org.iesfm.highschool.forms.students.CreateStudentForm;
import org.iesfm.highschool.forms.students.ShowStudentsForm;
import org.iesfm.highschool.services.StudentsServiceClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HighschoolMainForm {
    private StudentsServiceClient studentsServiceClient = new StudentsServiceClient();
    private JPanel mainPanel;
    private JButton showStudentsButton;
    private JButton showGroupsButton;
    private JButton addStudentQuickAction;

    public HighschoolMainForm(JFrame frame) {
        frame.setContentPane(mainPanel);
        createMenuBar(frame);
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
                CreateStudentForm createStudentForm = new CreateStudentForm(studentsServiceClient);
                // Asignamos el formulario al dialog
                dialog.setContentPane(createStudentForm.mainPanel);
                dialog.pack();
                dialog.setVisible(true);
            }
        });
    }

    public void createMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("Menu 1");
        menuBar.add(menu1);
        JMenuItem item11 = new JMenuItem("Item 1");
        menu1.add(item11);

        frame.setJMenuBar(menuBar);
    }

    private void showGroupsForm() {
        deleteCenter(mainPanel);
        CreateStudentForm form = new CreateStudentForm(studentsServiceClient);
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
        ShowStudentsForm form = new ShowStudentsForm(studentsServiceClient);
        mainPanel.add(form.mainPanel, BorderLayout.CENTER);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        var form = new HighschoolMainForm(frame);
        frame.pack();
        frame.repaint();
        frame.revalidate();
    }
}
