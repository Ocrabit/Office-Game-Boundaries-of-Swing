package PaperMill.sidePanel;

import Main.GamePanel;
import PaperMill.main.PaperMill;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.lightGray;

public class PaperMillPanel extends JPanel {
    //Layout Managers
    GridBagLayout layout = new GridBagLayout();

    //Daddy parent panel
    GamePanel gamePanel;

    //Game Launching calls
    protected PaperMill paperMill;
    protected boolean gameStatus = false;


    //Two main panels
    protected JPanel hirePanel = new JPanel();
    protected JPanel managePanel = new JPanel();

    //Dimensions
    protected Dimension originalFrame = new Dimension(256,768);


    public PaperMillPanel(){
        setSize(originalFrame);
        setPreferredSize(originalFrame);
        setVisible(true);
        setBackground(lightGray);
        hirePanel.setBackground(lightGray);
        managePanel.setBackground(lightGray);

        managePanel.add(new JButton("Click Me. Paper!"));
        add(managePanel, BorderLayout.CENTER);

        gameLauncher();

        validate();
    }

    public void gameLauncher(){
        JButton launchGame = new JButton("Launch");
        launchGame.addActionListener(e -> {
            gameStatus = !gameStatus;
            gamePanel.managePaperMillGame(gameStatus);
            if(gameStatus){
                launchGame.setText("Close");
            } else {
                launchGame.setText("Launch");
            }
            if(paperMill == null) {
                paperMill = gamePanel.paperGame;
            }
        });
        add(launchGame);
    }



    public void setGamePanel(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
}

