package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); //return a number of key pressed

        // TITLE STATE
        if(gp.gameState == gp.titleState){
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                if(gp.ui.commandNum > 0){
                    gp.ui.commandNum--;
                }
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                if(gp.ui.commandNum < 1){
                    gp.ui.commandNum++;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0 && gp.ui.difficultyOpt == false){
                    gp.ui.difficultyOpt = true;
                }
                else if(gp.ui.commandNum == 0 && gp.ui.difficultyOpt == true){
                    gp.gameDifficulty = gp.NormalDiff;
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                else if(gp.ui.commandNum == 1 && gp.ui.difficultyOpt == true){
                    gp.gameDifficulty = gp.HardDiff;
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                else if(gp.ui.commandNum == 1){
                    System.exit(0);
                }
            }
        }

        // PLAYING STATE
        else if(gp.gameState == gp.playState){
            System.out.println(gp.gameState);
            if(code == KeyEvent.VK_W){
                upPressed = true;
            }
            if(code == KeyEvent.VK_S){
                downPressed = true;
            }
            if(code == KeyEvent.VK_A){
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D){
                rightPressed = true;

                // gp.gameState = gp.gameOverState;
            }
            if(code == KeyEvent.VK_P){
                gp.gameState = gp.pauseState;
            }
        }

        // PAUSE STATE
        else if(gp.gameState == gp.pauseState){
            if(code == KeyEvent.VK_P){
                gp.gameState = gp.playState;
            }
        }
        // GAMEOVER STATE
        else if(gp.gameState == gp.gameOverState){
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                if(gp.ui.commandNum > 0){
                    gp.ui.commandNum--;
                }
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                if(gp.ui.commandNum < 1){
                    gp.ui.commandNum++;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.restart();
                    //gp.gameState = gp.playState;
                    //gp.playMusic(0);
                }
                if(gp.ui.commandNum == 1){
                    System.exit(0);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }

    }
}
