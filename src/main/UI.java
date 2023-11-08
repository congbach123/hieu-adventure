package main;

import object.OBJ_Key;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B, futura_40, futura_80B, pixelSans_100B, pixelSans_40, JosefinSans_40;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public boolean loseCondition = false;
    public int commandNum = 0;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#");

    public Color colorBase = new Color(0xBDD2B6);
    public Color colorBorder = new Color(0x2C3639);
    public Color colorBorder2 = new Color(0x213555);
    public Color colorSpecial = new Color(0xF8EDE3);
    public Color colorTitle = new Color(0x3F7CAC);
    public Color colorTitle2 = new Color(0xF5EFE7);
    public Color colorTitle3 = new Color(0xF67280);

    public UI(GamePanel gp){
        this.gp = gp;
/*
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        */
        try {
            InputStream is = getClass().getResourceAsStream("/font/PixelifySans-VariableFont_wght.ttf");
            pixelSans_100B = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 100f);
            is = getClass().getResourceAsStream("/font/JosefinSans-SemiBold.ttf");
            JosefinSans_40 = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 40f);

        }
        catch (FontFormatException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        futura_40 = new Font("futura", Font.PLAIN, 40);
        futura_80B = new Font("futura", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){

        this.g2 = g2;

        //TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        // PLAY STATE
        if(gp.gameState == gp.playState){
            if(gameFinished){
                gp.gameState = gp.gameOverState;
            }
            else {
                g2.setFont(futura_40);
                g2.setColor(colorBase);
                g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
                //CREATE BORDER AROUND TEXT, DISPLAY KEY

                g2.setColor(colorBorder);
                for (int i = -2; i <= 2; i++) {
                    for (int j = -2; j <= 2; j++) {
                        if (i != 0 || j != 0) {
                            g2.drawString("Key = " + gp.player.hasKey, 112 + i, 80 + j);
                        }
                    }
                }

                g2.setColor(colorBase);
                g2.drawString("Key = " + gp.player.hasKey, 112, 80);

                playTime +=(double) 1/60;

                // LOSE CONDITION
                if(playTime >= 60 && gameFinished!=true){
                    gameFinished = true;
                    loseCondition = true;
                }

                // TIME DISPLAY
                g2.setColor(colorBorder);
                for (int i = -2; i <= 2; i++) {
                    for (int j = -2; j <= 2; j++) {
                        if (i != 0 || j != 0) {
                            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*11 + i, 80 + j);
                        }
                    }
                }
                g2.setColor(colorBase);
                g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*11, 80);
                //MESSAGE
                if(messageOn == true){
                    g2.setFont(g2.getFont().deriveFont(30f));
                    //g2.drawString(message, gp.screenWidth/2 - gp.tileSize, gp.screenHeight - gp.tileSize);

                    //int textLength = (int) g2.getFontMetrics().getStringBounds(message, g2).getWidth();
                    int x = getXforCenteredText(message);
                    int y = gp.screenHeight / 2 - (gp.tileSize*3);

                    g2.setColor(colorBorder);
                    for (int i = -2; i <= 2; i++) {
                        for (int j = -2; j <= 2; j++) {
                            if (i != 0 || j != 0) {
                                g2.drawString(message,x + i, y + j);
                            }
                        }
                    }

                    g2.setColor(colorBase);
                    g2.drawString(message,x,y);

                    messageCounter++;

                    if(messageCounter > 120){
                        messageCounter =0;
                        messageOn = false;
                    }
                }
            }
        }
        // PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        if(gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }

    }

    public void drawTitleScreen(){

        BufferedImage backgroundImage = null;
        try {
            InputStream is = getClass().getResourceAsStream("/title/title.png");
            backgroundImage = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
        }

        // TITLE NAME
        g2.setFont(pixelSans_100B);
        String text = "Hieu Adventure";

        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;

        g2.setColor(colorBorder);
        for (int i = -5; i <= 5; i++) {
            for (int j = -5; j <= 5; j++) {
                if (i != 0 || j != 0) {
                    g2.drawString(text, x + i, y + j);
                }
            }
        }

        // SHADOW
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);

        // MAIN COLOR
        g2.setColor(colorTitle);
        g2.drawString(text, x, y);

        // MENU
        g2.setFont(JosefinSans_40);
        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize*6;

        g2.setColor(colorBorder2);
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (i != 0 || j != 0) {
                    g2.drawString(text, x + i, y + j);
                }
            }
        }
        g2.setColor(colorTitle2);
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.setColor(colorTitle3);
            g2.drawString(text, x, y);
        }

        g2.setFont(JosefinSans_40);
        text = "EXIT";
        x = getXforCenteredText(text);
        y += gp.tileSize;

        g2.setColor(colorBorder2);
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (i != 0 || j != 0) {
                    g2.drawString(text, x + i, y + j);
                }
            }
        }
        g2.setColor(colorTitle2);
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.setColor(colorTitle3);
            g2.drawString(text, x, y);
        }

    }
    public void drawPauseScreen(){
        g2.setFont(futura_80B);
        String text = "PAUSED";

        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2 - gp.tileSize;

        g2.setColor(colorBorder);
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (i != 0 || j != 0) {
                    g2.drawString(text, x + i, y + j);
                }
            }
        }

        g2.setColor(colorSpecial);
        g2.drawString(text,x,y);
    }

    public void drawGameOverScreen(){

        g2.setFont(futura_40);

        String text;
        int textLength;
        int x,y;

        if(loseCondition){
            text = "You did not escape in time";
        }
        else {
            text = "You escaped after " + dFormat.format(playTime) + " seconds!";
        }

        x = getXforCenteredText(text);
        y = gp.screenHeight / 2 - (gp.tileSize*3);

        g2.setColor(colorBorder);
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (i != 0 || j != 0) {
                    g2.drawString(text, x + i, y + j);
                }
            }
        }

        g2.setColor(colorBase);
        g2.drawString(text,x,y);

//            text = "You took "+ dFormat.format(playTime) + " seconds to escape.";
//            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//
//            x = gp.screenWidth / 2 - textLength/2;
//            y = gp.screenHeight / 2 - (gp.tileSize*4);
//            g2.drawString(text,x,y);

        g2.setFont(futura_80B);

        if(loseCondition){
            text = "GAME OVER";
        }
        else {
            text = "ESCAPED";
        }

        x = getXforCenteredText(text);
        y = gp.screenHeight / 2 - gp.tileSize;

        g2.setColor(colorBorder);
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (i != 0 || j != 0) {
                    g2.drawString(text, x + i, y + j);
                }
            }
        }

        g2.setColor(colorSpecial);
        g2.drawString(text,x,y);
        // end option
        g2.setFont(JosefinSans_40);
        text = "MAIN MENU";
        x = getXforCenteredText(text);
        y += gp.tileSize*2;

        g2.setColor(colorBorder2);
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (i != 0 || j != 0) {
                    g2.drawString(text, x + i, y + j);
                }
            }
        }
        g2.setColor(colorTitle2);
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.setColor(colorTitle3);
            g2.drawString(text, x, y);
        }

        g2.setFont(JosefinSans_40);
        text = "EXIT";
        x = getXforCenteredText(text);
        y += gp.tileSize;

        g2.setColor(colorBorder2);
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (i != 0 || j != 0) {
                    g2.drawString(text, x + i, y + j);
                }
            }
        }
        g2.setColor(colorTitle2);
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.setColor(colorTitle3);
            g2.drawString(text, x, y);
        }


        //gp.gameThread = null;
    }

    public int getXforCenteredText(String text){
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - textLength/2;
        return x;
    }
}
