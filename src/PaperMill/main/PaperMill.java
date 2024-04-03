package PaperMill.main;

import PaperMill.entity.Player;
import PaperMill.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class PaperMill extends JPanel implements Runnable{
    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 768 pixels

    //FPS
    int FPS = 60;

    public CollisionChecker collisionChecker = new CollisionChecker(this);
    TileManager tileM = new TileManager(this);
    Player player;
    KeyHandler keyHandler = new KeyHandler();
    Thread paperMillThread;

    public PaperMill(){
        this.setPreferredSize((new Dimension(screenWidth, screenHeight)));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        player = new Player(this, keyHandler);
    }

    public void startPaperMillThread() {
        paperMillThread = new Thread(this);
        paperMillThread.start();
    }


    @Override
    public void run() {
        double drawInterval = (double) 1000000000/FPS;  // 0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(paperMillThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    boolean first = true;
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("paperMill");

        PaperMill paperMill = new PaperMill();
        window.add(paperMill);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        paperMill.startPaperMillThread();
    }
}
