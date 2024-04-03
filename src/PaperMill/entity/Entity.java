package PaperMill.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    int x, y;
    int speed;

    public BufferedImage up1, up2, left1, left2, down1, down2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea ;
    public boolean collision = false;



}

