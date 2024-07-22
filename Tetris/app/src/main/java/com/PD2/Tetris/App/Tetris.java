package com.PD2.Tetris.App;

import com.PD2.Tetris.block.*;
import com.PD2.Tetris.shape.*;

//import com.PD2.Tetris.Game.GameController;
import com.PD2.Tetris.block.Tetromino;

import com.PD2.Tetris.Game.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.TimerTask;
import java.util.Timer;

public class Tetris extends JPanel implements KeyListener {

    private Timer timer;
    private final int delay = 500;
    private boolean isPaused;
    private boolean isOver = false;
    private Tetromino currentTetromino;
    private Tetromino nextTetromino;

    public static Wall wall;

    private Tetromino holdTetromino;
    private boolean holdUsed;
    //private Wall wall;
    private JFrame gameFrame;
    private scoreEstimate scoreManager;

    Tetris() {
        nextTetromino = Tetromino.random();
        //wall=new Wall();
        gameFrame = new JFrame("NCKU Tetris");
        timer = new Timer();
        isPaused = false;
        setFocusable(true);
        scoreManager = new scoreEstimate();
        setFocusTraversalKeysEnabled(false);
        currentTetromino = Tetromino.random();
        addKeyListener(this);
    }

// loading pictures
    public static BufferedImage I;
    public static BufferedImage J;
    public static BufferedImage L;
    public static BufferedImage O;
    public static BufferedImage S;
    public static BufferedImage T;
    public static BufferedImage Z;
    public static BufferedImage background;

    static {
        try {
            I = ImageIO.read(new File("img/I.png"));
            J = ImageIO.read(new File("img/J.png"));
            L = ImageIO.read(new File("img/L.png"));
            O = ImageIO.read(new File("img/O.png"));
            S = ImageIO.read(new File("img/S.png"));
            T = ImageIO.read(new File("img/T.png"));
            Z = ImageIO.read(new File("img/Z.png"));
            background = ImageIO.read(new File("img/background.png"));
            wall = new Wall();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void holdCurrentTetromino() {
        if (!holdUsed) {
            if (holdTetromino == null) {
                holdTetromino = currentTetromino;
                spawnNewTetromino();
            } else {
                Tetromino temp = currentTetromino;
                currentTetromino = holdTetromino;
                holdTetromino = temp;
            }
            holdUsed = true;
            repaint();
        }
    }

    public void endGame() {
        // game over
        System.out.println("Game Over");
        gameFrame.dispose();

        End_Menu end_menu = new End_Menu(scoreManager.getTotalScore());
        end_menu.play_again_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("game start!!!");
                end_menu.end_frame.dispose();
                Tetris panel = new Tetris();  //start button new _frame
                JFrame game_frame = panel.gameFrame;
                game_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                //GameController gameController = new GameController(game_frame);
                game_frame.setSize(810, 940);
                game_frame.add(panel);
                //game_frame.add(gameController);
                game_frame.setVisible(true);
                wall = new Wall();

                panel.start();

            }
        });
        end_menu.Menu_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                end_menu.end_frame.dispose();
                Menu menu = new Menu(); //new 
                menu.start_button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        System.out.println("game start!!!");
                        menu.frame.dispose();
                        Tetris panel = new Tetris();  //start button new
                        //JFrame game_frame = new JFrame("NCKU Tetris");
                        JFrame game_frame = panel.gameFrame;
                        game_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                        //GameController gameController = new GameController(game_frame);
                        game_frame.setSize(810, 940);
                        game_frame.add(panel);
                        //game_frame.add(gameController);
                        game_frame.setVisible(true);
                        wall = new Wall();
                        panel.start();
                    }
                });
            }
        });
        //isOver=true;
        
    public void spawnNewTetromino() {

//        if (wall.add(currentTetromino) == Wall.LOSE) {
//            currentTetromino=null;
//            endGame();
//        }
//        else {
        currentTetromino = nextTetromino;
        nextTetromino = Tetromino.random();
        repaint();
//        }

    }
}
}
