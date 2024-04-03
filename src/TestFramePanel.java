import Office.Employee;
import Office.ItemContainer;

import javax.swing.*;
import java.awt.*;

public class TestFramePanel extends JFrame {
    GridBagLayout gbl = new GridBagLayout();
    BorderLayout bl = new BorderLayout();

    JPanel panel = new JPanel(gbl);

    public TestFramePanel(ItemContainer c) {
        setLayout(bl);
//        System.out.println(c.getParent().getParent().getLocation().getX()-c.getWidth());
//        System.out.println(c.getLocation().getY());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);

        panel.setBackground(Color.decode("#e8e8e8"));
        createAndShowUI(c.getEmployee());
        add(panel, BorderLayout.CENTER);

        setVisible(true);
        revalidate();
        pack();
    }

    public void createAndShowUI(Employee employee) {
        //copy the same format as the way you lay out panels for managePanel
        GridBagConstraints c = new GridBagConstraints();
        panel.setBackground(Color.lightGray);

        //Close Button
        JButton closeButton = new JButton("X");
        //ActionListener
        closeButton.addActionListener(e -> dispose());
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.8;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        panel.add(closeButton, c);

        //Creating the list of employees
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.insets = new Insets(2, 0, 2, 0);
        c.gridy = 1;
        c.gridx = 0;
        c.gridwidth = 2;

        ItemContainer tempIc = new ItemContainer();
        tempIc.fillContainer(employee, 0);
        panel.add(tempIc, c);

    }
}