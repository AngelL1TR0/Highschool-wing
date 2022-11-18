package forms;

import service.GroupsService;
import service.StudentsService;

import javax.swing.*;
import java.awt.*;

public class ShowGroupsForm {
    public JPanel mainPanel;
    private JTable groupsTable;
    private JButton añadirButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JPanel buttonsPanel;
    private JButton estudiantesButton;
    private JButton gruposButton;


    public ShowGroupsForm(GroupsService groupsService) {
        gruposButton.addActionListener(actionEvent -> {
            if(mainPanel.isShowing()){
                gruposButton.setBackground(Color.PINK);
            }else {
                gruposButton.setBackground(Color.WHITE);
            }
            mainPanel.repaint();
            mainPanel.revalidate();
        });
        estudiantesButton.addActionListener(actionEvent -> {
            if(mainPanel.isShowing()){
                estudiantesButton.setBackground(Color.PINK);
            } else {
                estudiantesButton.setBackground(Color.WHITE);
            }
            ShowStudentForm studentForm = new ShowStudentForm(new StudentsService());
            mainPanel.add(studentForm.mainPanel, BorderLayout.CENTER);
            mainPanel.repaint();
            mainPanel.revalidate();
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        var form = new ShowGroupsForm(new GroupsService());
        frame.setContentPane(form.mainPanel);

        frame.pack();
        frame.repaint();
        frame.revalidate();
    }
}
