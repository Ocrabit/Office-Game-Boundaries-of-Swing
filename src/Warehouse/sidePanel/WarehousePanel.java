package Warehouse.sidePanel;

import Main.GamePanel;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.lightGray;

public class WarehousePanel extends JPanel {
    //Layout Managers
    GridBagLayout layout = new GridBagLayout();

    //Daddy parent panel
    GamePanel gamePanel;

    //Two main panels
    protected JPanel ordersPanel = new JPanel();
    protected JPanel managePanel = new JPanel();

    //Dimensions
    protected Dimension originalFrame = new Dimension(256,768);


    public WarehousePanel(){
        setSize(originalFrame);
        setPreferredSize(originalFrame);
        setVisible(true);
        setBackground(lightGray);
        ordersPanel.setBackground(lightGray);
        managePanel.setBackground(lightGray);

        managePanel.add(new JButton("Click Me. Manage!"));
        add(managePanel, BorderLayout.CENTER);

        validate();
    }


    public void setGamePanel(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
}

