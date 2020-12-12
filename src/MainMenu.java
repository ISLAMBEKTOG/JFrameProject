import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends Container{
    private JButton add;
    private JButton list;
    private JButton sort;
    private JButton morethan;

    public MainMenu(){
        setSize(500,300);
        setLayout(null);

        add = new JButton("ADD");
        add.setLocation(100, 30);
        add.setSize(300,30);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.mainMenu.setVisible(false);
                Main.frame.addStudent.setVisible(true);
            }
        });
        add(add);

        list = new JButton("LIST");
        list.setLocation(100,90);
        list.setSize(300,30);
        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.mainMenu.setVisible(false);
                Main.frame.listStudent.setVisible(true);
            }
        });
        add(list);

        sort = new JButton("SORT");
        sort.setLocation(100,150);
        sort.setSize(300,30);
        sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.mainMenu.setVisible(false);
                Main.frame.sortStudent.setVisible(true);
            }
        });
        add(sort);

        morethan = new JButton("MORE THAN 20 YEARS");
        morethan.setLocation(100,210);
        morethan.setSize(300,30);
        morethan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.mainMenu.setVisible(false);
                Main.frame.moreThan.setVisible(true);
            }
        });
        add(morethan);

    }
}