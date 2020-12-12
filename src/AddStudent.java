import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudent extends Container{
    private JLabel name_l;
    private JTextField name;
    private JLabel surname_l;
    private JTextField surname;
    private JLabel age_l;
    private JTextField age;
    private JButton send;
    private JButton back;
    private JLabel message;

    public AddStudent(){
        setSize(500,300);
        setLayout(null);

        name_l = new JLabel("Name");
        name_l.setLocation(50,50);
        name_l.setSize(100,30);
        add(name_l);

        name = new JTextField();
        name.setLocation(200,50);
        name.setSize(250,30);

        add(name);

        surname_l = new JLabel("Surname");
        surname_l.setLocation(50,100);
        surname_l.setSize(100,30);
        add(surname_l);

        surname = new JTextField();
        surname.setLocation(200,100);
        surname.setSize(250,30);
        add(surname);

        age_l = new JLabel("Age");
        age_l.setLocation(50,150);
        age_l.setSize(100,30);
        add(age_l);

        age = new JTextField();
        age.setLocation(200,150);
        age.setSize(250,30);
        add(age);

        message = new JLabel();
        message.setLocation(180,15);
        message.setSize(250,30);
        add(message);

        send = new JButton("SEND");
        send.setLocation(50,200);
        send.setSize(190,30);
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameText = name.getText();
                String surnameText = surname.getText();
                int ageText = Integer.parseInt(age.getText());
                Student student = new Student(null, nameText, surnameText, ageText);

                Main.addStudent(student);

                name.setText("");
                surname.setText("");
                age.setText("");

                message.setText("Student added!!!");
            }
        });
        add(send);

        back = new JButton("BACK");
        back.setLocation(260,200);
        back.setSize(190,30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.mainMenu.setVisible(true);
                Main.frame.addStudent.setVisible(false);
            }
        });
        add(back);


    }
}