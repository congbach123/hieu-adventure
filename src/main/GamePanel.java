package main;

import entity.Player;
import object.OBJ_Key;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 32;
    final int scale = 2;

    public final int tileSize = originalTileSize * scale; // 64*64 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 1024 px
    public final int screenHeight = tileSize * maxScreenRow; // 768 px
    // WORLD SETTING
    public final int maxWorldCol = 45;
    public final int maxWorldRow = 30;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // Easter
    public boolean easter = false;

    // FPS
    int FPS = 60;

    // SYSTEM
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //  ENTITY AND OBJECT HERE
    public Player player = new Player(this, keyH);
    public ArrayList<SuperObject> obj = new ArrayList<>();

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setupGame(){
        aSetter.setBoots();
        aSetter.setChest();
        aSetter.setDoor();
        aSetter.setKeyRandom();

        //playMusic(0); // play gamemusic

        gameState = titleState;
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
//    public void run() {
//
//        double drawInterval = 1000000000/FPS;
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//        while(gameThread != null){
////            System.out.println("The game loop is running");
////            1. Update information (player pos?)
//            update();
////            2. Draw the screen
//            repaint();
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1000000;
//
//                if(remainingTime < 0){
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long) remainingTime); // sleep() only accept long
//
//                nextDrawTime += drawInterval;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;



        while(gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){ // Every second print a fps count
                System.out.println("FPS: " + drawCount);
                drawCount =0;
                timer =0;
            }

        }
    }
    public void update(){

        if(gameState == playState){
            player.update();
        }
        if(gameState == pauseState){
            //nothing
        }

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // TITLE SCREEN
        if(gameState == titleState){
            ui.draw(g2);
        }
        //OTHER
        else {
// TILE
            tileM.draw(g2, 0, tileM.tile[0]);
            if(easter){
                tileM.draw(g2, 1, tileM.tile[1]); //EasterEgg
            }
            tileM.draw(g2, 2, tileM.tile[2]);
            tileM.draw(g2, 3, tileM.tile[3]);
            tileM.draw(g2, 4, tileM.tile[4]);
            tileM.draw(g2, 5, tileM.tile[5]);
            tileM.draw(g2, 6, tileM.tile[6]);
            tileM.draw(g2, 7, tileM.tile[7]);
            tileM.draw(g2, 8, tileM.tile[8]);

            // OBJECT
            for (int i = 0; i < obj.size(); i++){
                SuperObject objIndex = obj.get(i);
                if (objIndex!=null){ //Avoid nullpointer error
                    objIndex.draw(g2,this);
                }
            }

            // PLAYER
            player.draw(g2);

            // UI
            ui.draw(g2);
        }

        g2.dispose();
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
}
