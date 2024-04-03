package PaperMill.tile;

import PaperMill.main.PaperMill;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class TileManager {
    PaperMill pm;
    Tile[] tile;

    public TileManager(PaperMill pm) {
        this.pm = pm;

        tile = new Tile[10];

        getTileImage();
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/PixelTree.png")));
//
//            tile[2] = new Tile();
//            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/hut.png")));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        //alt does not work
        while(col < pm.maxScreenCol && row < pm.maxScreenRow) {
            g2.drawImage(tile[0].image, x, y, pm.tileSize, pm.tileSize, null);

            col++;
            x += pm.tileSize;


            if(col == pm.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += pm.tileSize;
            }
        }

        x = 0;
        y = 0;
        for(row = 0; row <= pm.maxScreenRow/2 -1; row+=2) {
            for (col = 0; col < pm.maxScreenCol/2; col+=2) {
                g2.drawImage(tile[1].image, x+(col* pm.tileSize), y+(row* pm.tileSize), pm.tileSize, pm.tileSize*2, null);

            }
        }


    }
}
