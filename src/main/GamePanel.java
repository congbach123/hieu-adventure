package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 32;
    final int scale = 2;

    final int tileSize = originalTileSize * scale; // 64*64 tile
    final int maxScreenCol = 16;
    final int maxSCreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 1024 px
    final int screenHeight = tileSize * maxSCreenRow; // 768 px

    Thread gameThread;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

    }
}
