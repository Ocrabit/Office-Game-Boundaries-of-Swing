package Office;

import javax.swing.*;
import java.awt.*;

public class SelectAdvertisement extends JPanel implements Runnable{
    SalesPanel parentPanel;
    Responders re;
    JLabel title = new JLabel("Select Advertisement");
    JLabel adPrice_1 = new JLabel("150", new ImageIcon("src/resources/icons/Currency.png"), JLabel.LEFT );
    JLabel adPrice_2 = new JLabel("400", new ImageIcon("src/resources/icons/Currency.png"), JLabel.LEFT );
    JLabel adTitle_1 = new JLabel("Newspaper Ad");
    JLabel adTitle_2 = new JLabel("Television Ad");
    int adTime_1 = 20; //minutes
    int adTime_2 = 40;
    JLabel labelAdTime_1 = new JLabel((adTime_1 + " min"), new ImageIcon("src/resources/icons/Currency.png"), JLabel.LEFT ); //minutes
    JLabel labelAdTime_2 = new JLabel((adTime_2 + " min"), new ImageIcon("src/resources/icons/Currency.png"), JLabel.LEFT); //minutes
    double runningTime = 0;
    JLabel runningTimeLabel = new JLabel("Running Ad: " + String.format("%.2f",runningTime) + " minutes");
    boolean runningTimeBusy;
    JButton buyButtonAd_1 = new JButton("Buy1");
    JButton buyButtonAd_2 = new JButton("Buy2");
    JLabel respondersLabel = new JLabel("Office.Responders: ");

    public SelectAdvertisement(Responders re, SalesPanel parentPanel){
        //Connect the responders panel
        this.re = re;
        this.parentPanel = parentPanel;

        setVisible(true);
        setEnabled(true);
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(256,184));
        setBackground(Color.LIGHT_GRAY);
        GridBagConstraints c = new GridBagConstraints();

        //Set-up Labels
        adTitle_1.setIconTextGap(2);
        adTitle_2.setIconTextGap(2);
        adTitle_1.setFont(new Font("Serif", Font.PLAIN, 16));
        adTitle_2.setFont(new Font("Serif", Font.PLAIN, 16));


        //TITLE
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(10,0,0,0);
        c.weightx = 0;
        c.weighty = 0;
        c.gridwidth = 3;
        add(title, c);

        //AdTITLE_1
        c.insets = new Insets(0,0,0,0);
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0.3;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        add(adTitle_1, c);

        //labelAdTime_1
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.SOUTH;
        c.weightx = 0.3;
        c.weighty = 0.3;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        add(labelAdTime_1, c);

        //adPrice_1
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.SOUTH;
        c.weightx = 0.3;
        c.weighty = 0.3;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        add(adPrice_1, c);

        //buyButtonAd_1
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.SOUTH;
        c.weightx = 0.3;
        c.weighty = 0.1;
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        add(buyButtonAd_1, c);

        //AdTITLE_2
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0.3;
        c.weighty = 0.3;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        add(adTitle_2, c);

        //labelAdTime_2
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.SOUTH;
        c.weightx = 0.3;
        c.weighty = 0.3;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        add(labelAdTime_2, c);

        //adPrice_2
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.SOUTH;
        c.weightx = 0.3;
        c.weighty = 0.3;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        add(adPrice_2, c);

        //buyButtonAd_2
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.SOUTH;
        c.weightx = 0.3;
        c.weighty = 0.3;
        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 1;
        add(buyButtonAd_2, c);

        //RunningTimeLabel
        c.insets = new Insets(0,10,0,0);
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 0.3;
        c.weighty = 0.4;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 3;
        add(runningTimeLabel, c);

        //Set-up Labels
        respondersLabel.setFont(new Font("Serif", Font.PLAIN, 16));

        //TITLE
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(10,0,0,0);
        c.weightx = 0;
        c.weighty = 0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 4;
        add(respondersLabel, c);


        buyButtonAd_1.addActionListener(e -> {
            /*if(!runningTimeBusy) {
                runningTimeBusy = true;
                runningTime += adTime_1;
                updateRunningTime();
            }*/
            runningTime = 0.1;
        });

        buyButtonAd_2.addActionListener(e -> {
            if(!runningTimeBusy) {
                runningTimeBusy = true;
                runningTime += adTime_2;
                updateRunningTime();
            }
        });

    }
    public void updateRunningTime(){
        runningTimeLabel.setText("Running Ad: " + String.format("%.2f",runningTime) + " minutes");
        if(runningTime <= 0) {
            runningTimeBusy = false;
            runningTime = 0;
            runningTimeLabel.setText("Running Ad: " + String.format("%.2f",runningTime) + " minutes");
            System.out.println("Retrieving Office.Responders");
            setResponders();
        }
    }

    public void setResponders() {
        re.createAdvertisementResponse();
        parentPanel.updatePanel();
    }


    Thread AdvertisementThread;
    public void AdvertisementThreadStart(){
        AdvertisementThread = new Thread(this);
        AdvertisementThread.start();
    }

    double halfSecondCount;
    int FPS = 60;

    @Override
    public void run() {
        double drawInterval = (double) 1000000000/FPS;
        double delta = 0;
        double lastTime = System.nanoTime();
        double currentTime;


        while(AdvertisementThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                //System.out.println("delta: " + delta);
                update(drawInterval * (1 + (1 - 1 / delta)));
                delta--;
            }
        }
    }

    private void update(double timePassed) {
        if(runningTimeBusy) {
            halfSecondCount += timePassed;
            if (halfSecondCount >= 500000000L) {
                runningTime -= (double) 1 / 120;

                halfSecondCount = 0;
            }
            updateRunningTime();
        }
    }

    /*public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setSize(new Dimension(256,212));
        Office.Responders re = new Office.Responders();
        Office.SelectAdvertisement sa = new Office.SelectAdvertisement(re, new Office.SalesPanel());
        f.add(sa);
        f.setVisible(true);

        sa.AdvertisementThreadStart();

    }*/
}
