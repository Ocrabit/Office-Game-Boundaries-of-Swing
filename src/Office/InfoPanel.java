package Office;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;


public class InfoPanel extends JFrame{

    GridBagLayout gbl = new GridBagLayout();
    BorderLayout bl = new BorderLayout();
    JPanel infoPanel = new JPanel();
    protected JProgressBar levelProgress = new JProgressBar();
    Employee employee;
    ItemContainer ic;
    boolean isEmployedOnPanel;
    SalesPanel parentPanel;
    protected AvailableEmployees availEmployPanel;

    public InfoPanel(ItemContainer c) {
        setLayout(bl);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setAlwaysOnTop(true);
        setFocusable(false);
        this.parentPanel = null;
        this.ic = c;
        isEmployedOnPanel = false;
        employee = c.getEmployee();
        employee.infoCreated(this);

        infoPanel.setBackground(Color.decode("#e8e8e8"));
        infoPanel.setLayout(gbl);
        createAndShowUI();
        add(infoPanel, BorderLayout.CENTER);
        setVisible(true);
        revalidate();
        pack();
        setLocation((int) (c.getLocationOnScreen().getX()-getWidth()-2), (int) c.getLocationOnScreen().getY());
    }

    public InfoPanel(ItemContainer c, boolean employedOnPanel, SalesPanel parentPanel) {
        setLayout(bl);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setAlwaysOnTop(true);
        setFocusable(false);
        this.parentPanel = parentPanel;
        this.ic = c;
        isEmployedOnPanel = employedOnPanel;
        employee = c.getEmployee();
        employee.infoCreated(this);

        infoPanel.setBackground(Color.decode("#e8e8e8"));
        infoPanel.setLayout(gbl);
        createAndShowUI();
        add(infoPanel, BorderLayout.CENTER);
        setVisible(true);
        revalidate();
        pack();
        setLocation((int) (c.getLocationOnScreen().getX()-getWidth()-2), (int) c.getLocationOnScreen().getY());
    }

    public void createAndShowUI(){  //"↓"  ⬆  ⬇
        infoPanel.removeAll();

        JLabel infoLabel = new JLabel(" Info");
        JButton closeButton = new JButton("X");
        JLabel nameLabel = new JLabel(employee.getName());
        JLabel levelLabel = new JLabel("Level: " + employee.getLevel());
        JLabel effectLabel1 = new JLabel(new ImageIcon("src/resources/icons/Clock.png"));
        JLabel effectLabel2 = new JLabel(new ImageIcon("src/resources/icons/Client_Avatar.png"));
        JLabel levelEffectLabel1 = new JLabel( "↑", new ImageIcon("src/resources/icons/Clock.png"), SwingConstants.LEFT);
        JLabel levelEffectLabel2 = new JLabel( "↑", new ImageIcon("src/resources/icons/Client_Avatar.png"), SwingConstants.RIGHT);

        closeButton.setPreferredSize(new Dimension(40,20));
        levelProgress.setPreferredSize(new Dimension(60,30));


        GridBagConstraints c = new GridBagConstraints();
        //Info Label
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.25;
        c.weighty = 0.2;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 6;
        c.ipady = 4;
        infoLabel.setBackground(Color.lightGray);
        infoLabel.setOpaque(true);
        infoLabel.setVerticalAlignment(SwingConstants.CENTER);
        infoLabel.setBorder(new MatteBorder(0,0,2,2, Color.BLACK));
        infoPanel.add(infoLabel, c);


        //Close Button
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.NORTHEAST;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.25;
        c.weighty = 0.2;
        c.gridx = 3;
        c.gridy = 0;
        infoPanel.add(closeButton,c);
        //add listener
        closeButton.addActionListener(e -> {
            //System.out.println("Clicked");
            safeDispose();
        });

        //Office.Employee Name
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 0.25;
        c.weighty = 1;
        c.gridx = 1;
        c.gridy = 0;
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        infoPanel.add(nameLabel, c);

        //Level Label
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.25;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 2;
        infoPanel.add(levelLabel, c);

        //First Effect Label
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.3;
        c.weighty = 1;
        c.gridx = 2;
        c.gridy = 2;
        effectLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(effectLabel1, c);

        //Second Effect Label
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.3;
        c.weighty = 1;
        c.gridx = 3;
        c.gridy = 2;
        effectLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(effectLabel2, c);

        //1st level effect Label
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.3;
        c.weighty = 2;
        c.gridx = 2;
        c.gridy = 4;
        infoPanel.add(levelEffectLabel1, c);

        //2nd level effect Label
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.3;
        c.weighty = 2;
        c.gridx = 2;
        c.gridy = 4;
        infoPanel.add(levelEffectLabel2, c);

        //Level in Progress or Waiting to level
        if(employee.isLeveling()){
            //LevelProgress bar
            c.insets = new Insets(0,4,0,4);
            c.fill = GridBagConstraints.NONE;
            c.anchor = GridBagConstraints.CENTER;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 0.3;
            c.weighty = 1;
            c.gridx = 3;
            c.gridy = 4;
            infoPanel.add(levelProgress,c);
            c.insets = new Insets(0,0,0,0);
        } else {
            JLabel levelCostTextLabel = new JLabel("Cost:");
            JLabel levelTimeCostLabel1 = new JLabel(employee.getCostTime(), new ImageIcon("src/resources/icons/Clock.png"), SwingConstants.CENTER);
            JLabel levelMoneyCostLabel2 = new JLabel(employee.getCostMoney(), new ImageIcon("src/resources/icons/Currency.png"), SwingConstants.CENTER);
            JButton levelUpButton = new JButton("Level Up");
            levelUpButton.setPreferredSize(new Dimension(60,30));

            c.insets = new Insets(10,0,0,0);
            //Level Cost Text Label
            c.fill = GridBagConstraints.NONE;
            c.anchor = GridBagConstraints.EAST;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 0.3;
            c.weighty = 1;
            c.gridx = 0;
            c.gridy = 3;
            infoPanel.add(levelCostTextLabel, c);

            //Level Time Cost Label
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.CENTER;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 0.3;
            c.weighty = 1;
            c.gridx = 2;
            c.gridy = 3;
            infoPanel.add(levelTimeCostLabel1, c);

            //Level Money Cost Label
            c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.CENTER;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 0.3;
            c.weighty = 1;
            c.gridx = 3;
            c.gridy = 3;
            infoPanel.add(levelMoneyCostLabel2, c);
            c.insets = new Insets(0,0,0,0);

            //LevelUpButton
            c.fill = GridBagConstraints.NONE;
            c.anchor = GridBagConstraints.CENTER;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 0.3;
            c.weighty = 1;
            c.gridx = 3;
            c.gridy = 4;
            infoPanel.add(levelUpButton, c);

            levelUpButton.addActionListener(e -> {
                System.out.println("CLicked Level");
                employee.attachedIC = ic;
                employee.startLevelUp();
            });
        }

        if(isEmployedOnPanel) {
            JButton removeFromPanel = new JButton("R");
            removeFromPanel.setPreferredSize(new Dimension(35,20));
            //LevelUpButton
            c.fill = GridBagConstraints.NONE;
            c.anchor = GridBagConstraints.CENTER;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 0.3;
            c.weighty = 1;
            c.gridx = 0;
            c.gridy = 4;
            infoPanel.add(removeFromPanel, c);
            removeFromPanel.addActionListener(e -> {
                if(parentPanel!=null) {
                    parentPanel.emptySlot(ic);
                    safeDispose();
                }
            });
        }

        infoPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));

        if(employee instanceof SalesEmployee) {
            effectLabel1.setText(((SalesEmployee)employee).getTotalSeconds() + " sec");
            effectLabel2.setText(((SalesEmployee)employee).getPercentChance() + "%");
        } else if (employee instanceof AccountingEmployee) {
            effectLabel1.setText("+" + ((AccountingEmployee)employee).getTimePercent() + " sec");
            effectLabel2.setText("+" + ((AccountingEmployee)employee).getClientPercent() + "%");
        } else {
            effectLabel1.setText("-" + ((HREmployee)employee).getTimePercent() + " sec");
            effectLabel2.setIcon(new ImageIcon("src/resources/icons/Currency.png"));
            effectLabel2.setText("+" + ((HREmployee)employee).getMoneyPercent() + "%");
        }

        pack();
    }
    public void safeDispose(){
        employee.infoRemoved();
        if(availEmployPanel != null) {
            availEmployPanel.infoPanel = null;
        }
        dispose();
    }

    public void updateProgressBar(int totalSeconds){
        levelProgress.setValue(totalSeconds);
    }

    public void setProgressCapacity(int capacity){
        levelProgress.setMaximum(capacity);
    }



}
