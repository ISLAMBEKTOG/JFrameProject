
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SortStudent extends Container {
    private JTable table;
    private JScrollPane scrollPane;
    private JButton back;

    public SortStudent() {
        setSize(500,300);
        setLayout(null);

        //headers for the table
        String[] columns = new String[] {
                "Id", "Name", "Surname", "Age"
        };

        //create table with data
        table = new JTable(Main.sortList(), columns);

        scrollPane = new JScrollPane(table);
        scrollPane.setLocation(0,0);
        scrollPane.setSize(500,200);
        add(scrollPane);

        back = new JButton("BACK");
        back.setLocation(200,215);
        back.setSize(100,30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.mainMenu.setVisible(true);
                Main.frame.sortStudent.setVisible(false);
            }
        });
        add(back);

    }

}