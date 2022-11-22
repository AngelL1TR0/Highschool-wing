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
    private JButton addGroupQuickAction;

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
                CreateStudentForm createStudentForm = new CreateStudentForm(studentsService);
                // Asignamos el formulario al dialog
                dialog.setContentPane(createStudentForm.mainPanel);
                dialog.pack();
                dialog.setVisible(true);
            }
        });
    }

    private void createMenuBar(JFrame frame) {
        ImageIcon image1 = new ImageIcon("src/main/resources/java.png");
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("Menú1");
        JMenu menu2 = new JMenu("Menú2");
        menuBar.add(menu1);
        menuBar.add(menu2);
        JMenuItem item11 = new JMenuItem("Menu item with Plain Text");
        JMenuItem item12 = new JMenuItem("Menu item with Text & Image");
        JMenuItem item13 = new JMenuItem("", image1);

        JRadioButtonMenuItem item21 =  new JRadioButtonMenuItem("Menu Item with Radio Button");
        JRadioButtonMenuItem item22 =  new JRadioButtonMenuItem("Menu Item 2 with Radio Button");

        JCheckBoxMenuItem item31 = new JCheckBoxMenuItem("Menu Item with Check box");
        JCheckBoxMenuItem item32 = new JCheckBoxMenuItem("Menu Item 2 with Check box");

        menu1.add(item11);
        menu1.add(item12);
        menu1.add(item13);
        menu1.add(item21);
        menu1.add(item22);
        menu1.add(item31);
        menu1.add(item32);

        frame = (JFrame) SwingUtilities.windowForComponent(mainPanel);
        frame.setJMenuBar(menuBar);
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
        var form = new HighschoolMainForm(frame);
        frame.setContentPane(form.mainPanel);

        frame.pack();
        frame.repaint();
        frame.revalidate();
    }

}