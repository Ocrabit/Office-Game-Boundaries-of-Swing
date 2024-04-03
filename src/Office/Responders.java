package Office;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;


public class Responders extends JPanel {
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();

    ItemContainer sampleItem = new ItemContainer();
    ItemContainer[] responderICs = new ItemContainer[3];
    Dimension waitingFieldSize = new Dimension(250, (int) ((sampleItem.getPreferredSize().getHeight()+6)*responderICs.length));
    JLabel waitingField = new JLabel();


    public Responders(){
        setVisible(true);
        setEnabled(true);
        setLayout(layout);
        setPreferredSize(new Dimension(256, (int) waitingFieldSize.getHeight()));
        setBackground(Color.lightGray);
        setWaitingResponse(false);

        waitingField.setHorizontalAlignment(SwingConstants.CENTER);
        waitingField.setVerticalAlignment(SwingConstants.CENTER);

    }

    void setWaitingResponse(boolean advertisementInProgress){
        if(advertisementInProgress) {waitingField.setText("Advertisement Ongoing");}
        else {waitingField.setText("No Advertisement Running");}
        removeAll();
        waitingField.setPreferredSize(waitingFieldSize);
        waitingField.setBackground(Color.white);
        waitingField.setOpaque(true);
        waitingField.setBorder(new CompoundBorder( // sets two borders
                BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK), // outer border
                BorderFactory.createEmptyBorder(10, 10, 10, 10))); //inner margin
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.CENTER;
        c.ipadx = ((getPreferredSize().width - waitingField.getPreferredSize().height) - 8);
        c.weightx = 1;
        c.weighty = 1;
        add(waitingField, c);
        System.out.println(waitingField.getSize());
    }


    public void setResponders() {
        removeAll();
        setBorder(null);
        setBackground(Color.lightGray);
        for (int i = 0; i < responderICs.length; i++) {
            c.insets = new Insets(2,0,2,0);
            c.gridy = i;
            add(responderICs[i], c);
        }
        revalidate();
//        System.out.println(getPreferredSize());
    }

    public void createAdvertisementResponse() {
        for (int i = 0; i < responderICs.length; i++) {
            responderICs[i] = new ItemContainer();
        }
        setResponders();
    }


    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setSize(new Dimension(256,212));
        Responders res = new Responders();
        f.add(res);
        f.setVisible(true);
        //f.pack();
    }
}
