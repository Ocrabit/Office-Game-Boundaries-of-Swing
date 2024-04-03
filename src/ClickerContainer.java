import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class ClickerContainer extends JPanel {
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    JLabel labelImage = new JLabel(new ImageIcon("src/resources/WoodsIcons/HorizontalBasicLog.png"));
    JProgressBar progressBar = new JProgressBar();
    JLabel timeDisplay = new JLabel();
    JLabel displayCount = new JLabel();
    JLabel costText = new JLabel();
    JLabel currencyLogo = new JLabel(new ImageIcon("src/resources/icons/Currency.png"));
    JButton buyButton = new JButton();


    float[] parameters = setParameters(0.1f,30);
    float cost = parameters[0];
    float value = parameters[1];
    float costIncrease = parameters[2];
    int amount = 0;
    int milestone = 15;
    double finalValue;
    int maxTime = 2;
    int differentialPlacement = 48;
    int buyAmount = 1;


    public ClickerContainer(){
        setVisible(true);
        setEnabled(true);
        setLayout(layout);
        setPreferredSize(new Dimension(256,64));
        setBackground(Color.lightGray);
        setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.black));

        c.insets = new Insets(2,0,2,0);
        labelImage.setPreferredSize(new Dimension(48,48));
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.25;
        c.weighty = 1;
        c.gridheight = 3;
        c.anchor = GridBagConstraints.CENTER;
//        c.fill = GridBagConstraints.BOTH;
        add(labelImage,c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.weightx = 0.5;
        c.weighty = 0.33;
        c.fill = GridBagConstraints.HORIZONTAL;
        timeDisplay.setBackground(Color.lightGray);
        timeDisplay.setIcon(new ImageIcon("src/resources/icons/Currency.png"));
        timeDisplay.setText(String.valueOf(maxTime));
        timeDisplay.setHorizontalAlignment(SwingConstants.LEFT);
        add(timeDisplay);

        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        displayCount.setBackground(Color.lightGray);
        displayCount.setHorizontalAlignment(SwingConstants.CENTER);
        displayCount.setText(amount + " / " + milestone);
        add(displayCount, c);

        c.gridx = 1;
        c.gridy = 2;
        c.gridheight = 1;
        c.weightx = 0.5;
        progressBar.setForeground(Color.PINK);
        progressBar.setBackground(Color.BLACK);
        progressBar.setValue(0);

        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 0.25;
        costText.setBackground(Color.lightGray);
        costText.setText(String.valueOf(cost));
        costText.setIcon(new ImageIcon("src/resources/icons/Currency.png"));
        costText.setHorizontalAlignment(SwingConstants.CENTER);
        add(costText, c);

        c.gridx = 2;
        c.gridy = 2;
        c.anchor = GridBagConstraints.CENTER;
        buyButton.setText("Buy " + buyAmount);
        add(buyButton, c);

    }


    public float[] setParameters(float valueReductionPercent, float cost){
        float costPercent = valueReductionPercent*(5); //(5/1)
        float costIncreasePercent = costPercent * ((float) 2/5);
        float value = cost*(valueReductionPercent/costPercent);
        float costIncrease = cost*(costIncreasePercent/costPercent);
        float[] parameters = {cost, value, costIncrease}; //[0] = cost | [1] = value | [2] = costIncrease
        return parameters;
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setSize(new Dimension(256,212));
        ClickerContainer cc = new ClickerContainer();
        f.add(cc);
        f.setVisible(true);
        f.pack();

    }
}
