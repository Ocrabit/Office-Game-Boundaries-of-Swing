package Office;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class ItemContainer extends JPanel {
    private Font font_8bit;
    private boolean emptyPanel;
    private String officeName;
    private Employee employee;


    JLabel employeeNameLabel = new JLabel();
    InfoPanel infoPanel;
    JButton infoButton = new JButton("INFO");
    JButton employButton = new JButton("EMPLOY");
    JLabel leftEffectLabel = new JLabel();
    JLabel rightEffectLabel = new JLabel();

    ItemContainer thisPanel;
    GridBagConstraints fillConstraint = new GridBagConstraints();

    public ItemContainer(){
        thisPanel = this;

        setVisible(true);
        setEnabled(true);
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(240,60));

        //load font_8bit
        loadFont();

        //Button sizes
        employButton.setPreferredSize(new Dimension(80,30));
        infoButton.setPreferredSize(new Dimension(60,30));

        JLabel labelImage = new JLabel(new ImageIcon("src/resources/ItemTab.png"));
        labelImage.setPreferredSize(new Dimension(240,60));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        c.gridheight = 2;
        add(labelImage,c);
        setBackground(Color.decode("#e8e8e8"));

    }

    public void emptyPanel() {
        if(getMouseListeners().length > 0) {
            MouseListener temp = getMouseListeners()[0];
            removeAll();
            //addMouseListener(temp);
        } else {
            removeAll();
        }

        JLabel labelImage = new JLabel(new ImageIcon("src/resources/ItemTab.png"));
        labelImage.setPreferredSize(new Dimension(240,60));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        c.gridheight = 2;
        add(labelImage,c);
    }

    public void emptyLabel(int num){ //[0] = Sales | [1] = Accounting | [2] = HR | [3] = Error assume HR
        emptyPanel();
        emptyPanel = true;

        //Set officeName
        if(num == 0) {officeName = "Sales";} else if(num == 1) {officeName = "Accounting";} else {officeName = "HR";}

        JLabel emptySlotLabel = new JLabel("Empty " + officeName +  " Slot");

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        c.gridheight = 2;
        c.anchor = GridBagConstraints.CENTER;
        emptySlotLabel.setHorizontalAlignment(SwingConstants.CENTER);
        emptySlotLabel.setFont(new Font("8-BIT WONDER", Font.PLAIN, 13));

        add(emptySlotLabel,c);
    }

    public void fillContainer(Employee employee, int num) {
        //num selects what it is being filled for
        //[0] = available employee list | [1] = employed to panel list
        //Set button sizes for sake of layout :)
        emptyPanel = false;
        emptyPanel();

        if(num == 0) {
            //Employ Button
            fillConstraint.fill = GridBagConstraints.NONE;
            fillConstraint.anchor = GridBagConstraints.SOUTH;
            fillConstraint.gridx = 2;
            fillConstraint.gridy = 1;
            fillConstraint.weightx = (double) 10/38;
            add(employButton,fillConstraint);

            //INfO Button
            fillConstraint.insets = new Insets(0,4,0,4);
            fillConstraint.gridx = 0;
            fillConstraint.gridy = 1;
            fillConstraint.weightx = (double) 9/38;
            add(infoButton, fillConstraint);
            fillConstraint.insets = new Insets(0,0,0,0);

            //Set Horizontal for the rest of the components
            fillConstraint.fill = GridBagConstraints.HORIZONTAL;
        } else if (num == 1) {
            //Set both for all components
            fillConstraint.fill = GridBagConstraints.BOTH;

            //Fix any label edits
            leftEffectLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        } else {
            System.out.println("Error in handling fillContainer num : Office.ItemContainer\n" + this);
        }

        if (employee instanceof SalesEmployee) {
            fillContainer((SalesEmployee) employee);
        } else if (employee instanceof AccountingEmployee) {
            fillContainer((AccountingEmployee) employee);
        } else {
            fillContainer((HREmployee) employee);
        }

    }


    private void fillContainer(SalesEmployee employee) {
        //Sales Container
        this.employee = employee;

        //EmployeeNameLabel
        fillConstraint.anchor = GridBagConstraints.SOUTH;
        fillConstraint.gridx = 0;
        fillConstraint.gridy = 0;
        fillConstraint.weightx = (double) 13/38;
        fillConstraint.weighty = 1;
        employeeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        employeeNameLabel.setText(employee.getName() +  "  " + employee.getLevel());
        add(employeeNameLabel, fillConstraint);

        //TimeLabel
        fillConstraint.anchor = GridBagConstraints.SOUTH;
        fillConstraint.gridx = 1;
        fillConstraint.gridy = 0;
        fillConstraint.weightx = (double) 15 / 38;
        leftEffectLabel.setIconTextGap(2);
        leftEffectLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftEffectLabel.setIcon(new ImageIcon("src/resources/icons/Clock.png"));
        leftEffectLabel.setText(employee.getTotalSeconds() / 60 + ":" + employee.getTotalSeconds() % 60 + "sec");
        add(leftEffectLabel, fillConstraint);

        //PercentLabel
        fillConstraint.anchor = GridBagConstraints.SOUTH;
        fillConstraint.gridx = 2;
        fillConstraint.gridy = 0;
        fillConstraint.weightx = (double) 10 / 38;
        rightEffectLabel.setIconTextGap(2);
        rightEffectLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rightEffectLabel.setIcon(new ImageIcon("src/resources/icons/Client_Avatar.png"));
        rightEffectLabel.setText((int)((100)*employee.getPercentChance()) + "%");
        rightEffectLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        add(rightEffectLabel, fillConstraint);
    }

    private void fillContainer(AccountingEmployee employee) {
        //Accounting Container
        this.employee = employee;

        //EmployeeNameLabel
        fillConstraint.anchor = GridBagConstraints.SOUTH;
        fillConstraint.gridx = 0;
        fillConstraint.gridy = 0;
        fillConstraint.weightx = (double) 13/38;
        fillConstraint.weighty = 1;
        employeeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        employeeNameLabel.setText(employee.getName() +  "  " + employee.getLevel());
        add(employeeNameLabel, fillConstraint);

        //TimeLabel
        fillConstraint.anchor = GridBagConstraints.SOUTH;
        fillConstraint.gridx = 1;
        fillConstraint.gridy = 0;
        fillConstraint.weightx = (double) 15 / 38;
        leftEffectLabel.setIconTextGap(2);
        leftEffectLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftEffectLabel.setIcon(new ImageIcon("src/resources/icons/Clock.png"));
        leftEffectLabel.setText("+" + String.format("%.0f", employee.getTimePercent()*100) + "%");
        add(leftEffectLabel, fillConstraint);

        //PercentLabel
        fillConstraint.anchor = GridBagConstraints.SOUTH;
        fillConstraint.gridx = 2;
        fillConstraint.gridy = 0;
        fillConstraint.weightx = (double) 13 / 38;
        rightEffectLabel.setIconTextGap(2);
        rightEffectLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rightEffectLabel.setIcon(new ImageIcon("src/resources/icons/Client_Avatar.png"));
        rightEffectLabel.setText("+" + String.format("%.0f", employee.getClientPercent()*100) + "%");
        rightEffectLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        add(rightEffectLabel, fillConstraint);
    }

    private void fillContainer(HREmployee employee) {
        //HR Container
        this.employee = employee;

        //EmployeeNameLabel
        fillConstraint.anchor = GridBagConstraints.SOUTH;
        fillConstraint.gridx = 0;
        fillConstraint.gridy = 0;
        fillConstraint.weightx = (double) 13/38;
        fillConstraint.weighty = 1;
        employeeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        employeeNameLabel.setText(employee.getName() +  "  " + employee.getLevel());
        add(employeeNameLabel, fillConstraint);

        //TimeLevel
        fillConstraint.anchor = GridBagConstraints.SOUTH;
        fillConstraint.gridx = 1;
        fillConstraint.gridy = 0;
        fillConstraint.weightx = (double) 15 / 38;
        leftEffectLabel.setIconTextGap(2);
        leftEffectLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftEffectLabel.setIcon(new ImageIcon("src/resources/icons/Clock.png"));
        leftEffectLabel.setText("+" + String.format("%.0f", employee.getMoneyPercent()*100) + "%");
        add(leftEffectLabel, fillConstraint);

        //PercentLabel
        fillConstraint.anchor = GridBagConstraints.SOUTH;
        fillConstraint.gridx = 2;
        fillConstraint.gridy = 0;
        fillConstraint.weightx = (double) 13 / 38;
        rightEffectLabel.setIconTextGap(2);
        rightEffectLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rightEffectLabel.setIcon(new ImageIcon("src/resources/icons/Client_Avatar.png"));
        rightEffectLabel.setText("" + String.format("%.0f", employee.getTimePercent()*100) + "%");
        rightEffectLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        add(rightEffectLabel, fillConstraint);
    }

    public boolean isEmpty(){
        return emptyPanel;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getOfficeName() {
        return officeName;
    }

    //Does not work figure it out
    private void loadFont(){
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/Fonts/8-BIT WONDER.TTF")));
        } catch (IOException|FontFormatException ignored){
            System.out.println("Failed loading");
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setSize(new Dimension(256,212));
        ItemContainer ic = new ItemContainer();
        f.add(ic);
        f.setVisible(true);
        f.pack();
    }

}
