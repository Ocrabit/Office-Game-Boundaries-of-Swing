package Office;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionTab extends JPanel {
    SalesPanel parentPanel;
    JButton hireButton = new JButton("Hire");
    JButton manageButton = new JButton("Manage");
    public SelectionTab(){
        setVisible(true);
        setEnabled(true);
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(256,36));
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
        //c.insets = new Insets(0,0,10,0);
        c.weighty = 0;
        c.weightx = 0.5;
        add(hireButton, c);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridwidth = GridBagConstraints.RELATIVE;
        add(manageButton, c);
        setBackground(Color.LIGHT_GRAY);

        hireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.switchPanel(true);
            }
        });

        manageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.switchPanel(false);
            }
        });

    }

    public void setThis(SalesPanel parentPanel) {
        this.parentPanel = parentPanel;
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setSize(new Dimension(256,212));
        SelectionTab st = new SelectionTab();
        f.add(st);
        f.setVisible(true);
        f.pack();
    }
}
