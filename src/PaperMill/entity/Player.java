package PaperMill.entity;

import PaperMill.main.KeyHandler;
import PaperMill.main.PaperMill;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    PaperMill pm;
    KeyHandler keyH;

    public Player(PaperMill pm, KeyHandler keyH) {
        this.pm = pm;
        this.keyH = keyH;

        solidArea = new Rectangle(8,16,32,32);
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "right";
    }
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Dwigt Sprites/Dwigt Up 1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Dwigt Sprites/Dwigt Up 2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Dwigt Sprites/Dwigt Left 1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Dwigt Sprites/Dwigt Left 2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Dwigt Sprites/Dwigt Down 1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Dwigt Sprites/Dwigt Down 2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Dwigt Sprites/Dwigt Right 1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Dwigt Sprites/Dwigt Right 2.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if(keyH.upPressed || keyH.leftPressed || keyH.downPressed || keyH.rightPressed) {
            spriteCounter++;
            if(spriteCounter > 12) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
        }} else { spriteNum = 1;}
        if(keyH.upPressed) {
            direction = "up";
            y -= speed;
        }  if (keyH.leftPressed){
            direction = "left";
            x-=speed;
        } if (keyH.downPressed){
            direction = "down";
            y+=speed;
        } if (keyH.rightPressed){
            direction = "right";
            x+=speed;
        }
//Implement collision eventually
//        collision = true;
//        pm.collisionChecker.checkTile(this);
    }

    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, pm.tileSize, pm.tileSize);

        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                } else if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, x, y, pm.tileSize, pm.tileSize, null);

    }
}
