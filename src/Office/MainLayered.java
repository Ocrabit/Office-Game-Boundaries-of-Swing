package Office;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainLayered extends JLayeredPane {
    MouseAdapter layeredListener;

    public MainLayered() {
        setPreferredSize(new Dimension(768, 672));
        setBackground(Color.white);
        setOpaque(true);
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK));

        addMouseListener(layeredListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                Object source = event.getSource();
                if (source instanceof JPanel panelPressed) {
                    //orderGame(panelPressed);
                    //System.out.println("Pressed: \n x: " + event.getX() + " and y: " + event.getY());
                }
            }
        });
    }
}
