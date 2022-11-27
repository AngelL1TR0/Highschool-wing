package org.iesfm.highschool.forms.students;

import org.iesfm.highschool.forms.students.CreateStudentForm;
import org.iesfm.highschool.forms.students.StudentContactInfoForm;
import org.iesfm.highschool.services.StudentsServiceClient;

import javax.swing.*;
import java.awt.*;

public class CreateStudentTabbedForm {
    public JPanel mainPanel;
    private JPanel generalPanel;
    private JPanel contactPanel;

    public CreateStudentTabbedForm(StudentsServiceClient studentsServiceClient) {
        CreateStudentForm generalForm = new CreateStudentForm(studentsServiceClient);
        generalPanel.add(generalForm.mainPanel, BorderLayout.CENTER);

        StudentContactInfoForm contactForm = new StudentContactInfoForm();
        contactPanel.add(contactForm.mainPanel, BorderLayout.CENTER);


    }
}
