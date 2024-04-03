package StoreFront.sidePanel;

import Main.GamePanel;

import javax.swing.*;
import java.awt.*;

public class StoreFrontPanel extends JPanel {
    //Layout Managers
    GridBagLayout layout = new GridBagLayout();

    //Daddy parent panel
    GamePanel gamePanel;

    //Two main panels
    protected JPanel buyPanel = new JPanel();
    protected JPanel inventoryPanel = new JPanel();

    //Dimensions
    protected Dimension originalFrame = new Dimension(256,768);


    public StoreFrontPanel(){
        setSize(originalFrame);
        setPreferredSize(originalFrame);
        setVisible(true);
        setBackground(Color.lightGray);
        buyPanel.setBackground(Color.lightGray);
        inventoryPanel.setBackground(Color.lightGray);

        inventoryPanel.add(new JButton("Click Me. Inventory!"));
        add(inventoryPanel, BorderLayout.CENTER);

        validate();
    }


    public void setGamePanel(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
}
