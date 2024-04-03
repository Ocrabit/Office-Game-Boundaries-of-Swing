package Main;

import Office.MainLayered;
import Office.SalesPanel;
import PaperMill.main.PaperMill;
import PaperMill.sidePanel.PaperMillPanel;
import StoreFront.sidePanel.StoreFrontPanel;
import Warehouse.sidePanel.WarehousePanel;

import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GamePanel extends JFrame {
    MouseAdapter pointLocation;
    GridBagLayout gbl = new GridBagLayout();

    int width = 1024;
    int height = 768;

    MainLayered layeredPane;
    public PaperMill paperGame;
    JPanel sideBar;
    JPanel[] sidePanels = new JPanel[4];
    JPanel buttonField; //contains buttons
    JPanel bottomBar;

    public GamePanel() {
        setLayout(gbl);
        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(300,200);
        setVisible(true);
        getContentPane().setBackground(Color.lightGray);

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.75;
        c.weighty = 0.875;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        layeredPane = new MainLayered();
        add(layeredPane, c);

        //Sales Panel
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;
        c.weightx = 0.25;
        c.weighty = 0;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 0;
        sideBar = new JPanel();
        sideBar.setBackground(Color.lightGray);
        add(sideBar, c);

        createSideBarPanels();

        //OrderBottomBar Panel
        c.weightx = 0.75;
        c.weighty = 0.125;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        bottomBar = new JPanel();
        bottomBar.setPreferredSize(new Dimension(768,96));  //Width = 768 || Height = 96
        bottomBar.setBackground(Color.PINK);
        bottomBar.setBorder(BorderFactory.createMatteBorder(2,0,0,2, Color.BLACK));
        add(bottomBar, c);

        addListeners();

        pack();
    }

    private void createSideBarPanels() {
        //initializing panels
        StoreFrontPanel storeFrontPanel = new StoreFrontPanel();
        storeFrontPanel.setGamePanel(this);
        sideBar.add(storeFrontPanel, BorderLayout.CENTER);
        storeFrontPanel.setEnabled(false);
        storeFrontPanel.setVisible(false);
        sidePanels[0] = storeFrontPanel;

        SalesPanel salesPanel = new SalesPanel();
        salesPanel.setGamePanel(this);
        sideBar.add(salesPanel, BorderLayout.CENTER);
        sidePanels[1] = salesPanel;

        WarehousePanel warehousePanel = new WarehousePanel();
        warehousePanel.setGamePanel(this);
        sideBar.add(warehousePanel, BorderLayout.CENTER);
        warehousePanel.setEnabled(false);
        warehousePanel.setVisible(false);
        sidePanels[2] = warehousePanel;

        PaperMillPanel paperMillPanel = new PaperMillPanel();
        paperMillPanel.setGamePanel(this);
        sideBar.add(paperMillPanel, BorderLayout.CENTER);
        paperMillPanel.setEnabled(false);
        paperMillPanel.setVisible(false);
        sidePanels[3] = paperMillPanel;

        //initialize button container
        buttonField = new JPanel();
        buttonField.setLayout(new GridBagLayout());
        GridBagConstraints bf = new GridBagConstraints(0,0,1,1,0.125,0,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(0,0,0,0), 0,0);

        //adding buttons to switch between panels
        JButton[] sidePanelButtons = new JButton[4];
        sidePanelButtons[0] = new JButton("StoreFront");
        sidePanelButtons[1] = new JButton("Office");
        sidePanelButtons[2] = new JButton("Warehouse");
        sidePanelButtons[3] = new JButton("PaperMill");
        //Add button listeners and action
        for (int i = 0; i < sidePanelButtons.length; i++) {
            int finalI = i;
            sidePanelButtons[i].addActionListener(e -> {
                for (int j = 0; j < sidePanels.length; j++) {
                    if(j!=finalI){
                        sidePanels[j].setEnabled(false);
                        sidePanels[j].setVisible(false);
                    }
                }
                sidePanels[finalI].setVisible(true);
                sidePanels[finalI].setEnabled(true);
                revalidate();
            });

            sidePanelButtons[i].setPreferredSize(new Dimension(80,30));
            buttonField.add(sidePanelButtons[i],bf);
            bf.gridx++;
        }
        bf.weightx = 0.7;
        buttonField.add(new JPanel(),bf);

        JPanel emptySpacePanel = new JPanel();

        layeredPane.setLayout(new GridBagLayout());
        GridBagConstraints c1 =new GridBagConstraints(0,0,1,1,1,0.05,
                GridBagConstraints.WEST,GridBagConstraints.BOTH,new Insets(0,0,0,0),0,0);
        layeredPane.add(buttonField, c1, JLayeredPane.PALETTE_LAYER);
        GridBagConstraints c2 =new GridBagConstraints(0,1,2,1,1,0.95,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(0,0,0,0),0,0);
        layeredPane.add(emptySpacePanel, c2, JLayeredPane.PALETTE_LAYER);


    }

    public void managePaperMillGame(boolean gameStatus) {
        if(gameStatus) {
            GridBagConstraints c = new GridBagConstraints();
            c.gridheight = 2;
            c.gridwidth = 2;
            c.fill = GridBagConstraints.BOTH;
            c.insets = new Insets(20,20,20,20);

            paperGame = new PaperMill();
            paperGame.startPaperMillThread();
            layeredPane.add(paperGame, c, JLayeredPane.DEFAULT_LAYER);
            System.out.println(paperGame.requestFocusInWindow());
        } else {
            paperGame.setEnabled(false);
            paperGame.setVisible(false);
            layeredPane.remove(paperGame);
            paperGame = null;
        }
    }



    public void addListeners(){
        addWindowListener(new WindowAdapter() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void windowDeactivated(WindowEvent e) {
                if(((SalesPanel)sidePanels[1]).infoPanel != null) {
                    ((SalesPanel)sidePanels[1]).infoPanel.setAlwaysOnTop(false);
                }
                if(((SalesPanel)sidePanels[1]).availEmployPanel != null) {
                    ((SalesPanel)sidePanels[1]).availEmployPanel.setAlwaysOnTop(false);

                    if(((SalesPanel)sidePanels[1]).availEmployPanel.infoPanel != null) {
                        ((SalesPanel)sidePanels[1]).availEmployPanel.infoPanel.setAlwaysOnTop(false);
                    }
                }
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void windowActivated(WindowEvent e) {
                if(((SalesPanel)sidePanels[1]).infoPanel != null) {
                    ((SalesPanel)sidePanels[1]).infoPanel.setAlwaysOnTop(true);
                }
                if(((SalesPanel)sidePanels[1]).availEmployPanel != null) {
                    ((SalesPanel)sidePanels[1]).availEmployPanel.setAlwaysOnTop(true);

                    if(((SalesPanel)sidePanels[1]).availEmployPanel.infoPanel != null) {
                        ((SalesPanel)sidePanels[1]).availEmployPanel.infoPanel.setAlwaysOnTop(true);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GamePanel main = new GamePanel();
            }
        });
    }
}
