import javax.swing.*;

public class MainFrame extends JFrame {
    public static AddStudent addStudent;
    public static ListStudent listStudent;
    public static SortStudent sortStudent;
    public static MoreThan moreThan;
    public static MainMenu mainMenu;

    public MainFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,300);
        setLayout(null);

        mainMenu = new MainMenu();
        mainMenu.setLocation(0,0);
        add(mainMenu);

        addStudent = new AddStudent();
        addStudent.setLocation(0,0);
        addStudent.setVisible(false);
        add(addStudent);

        listStudent = new ListStudent();
        listStudent.setLocation(0,0);
        listStudent.setVisible(false);
        add(listStudent);

        sortStudent = new SortStudent();
        sortStudent.setLocation(0,0);
        sortStudent.setVisible(false);
        add(sortStudent);

        moreThan = new MoreThan();
        moreThan.setLocation(0,0);
        moreThan.setVisible(false);
        add(moreThan);

        repaint();
    }
}