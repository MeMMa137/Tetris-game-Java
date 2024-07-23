package com.PD2.Tetris.Game;

import com.PD2.Tetris.App.End_Menu;
import com.PD2.Tetris.App.scoreEstimate;
import com.PD2.Tetris.block.Tetromino;
import com.PD2.Tetris.block.Wall;
import com.PD2.Tetris.block.Cell;




import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;



public class GameController extends JPanel implements KeyListener {

    private Timer timer;
    private boolean isPaused;
    private final int delay = 1000; 
    private Tetromino currentTetromino;
    private Tetromino nextTetromino; 
    private Tetromino holdTetromino;
    private boolean holdUsed;
    private Wall wall;
    private JFrame gameFrame; 
    private scoreEstimate scoreManager;

    public GameController(JFrame frame) {
        this.gameFrame = frame; 
        timer = new Timer();
        isPaused = false;
        holdUsed = false;
        wall = new Wall();
        scoreManager = new scoreEstimate(); 
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        nextTetromino = Tetromino.random(); 
    }

    public void start() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!isPaused) {
                    dropCurrentTetromino();
                }
            }
        }, 0, delay);

        spawnNewTetromino();
    }

    public void spawnNewTetromino() {
        currentTetromino = nextTetromino; 
        nextTetromino = Tetromino.random(); 
        if (wall.add(currentTetromino) == Wall.LOSE) {
            endGame();
        }
        repaint();
    }
    
public void dropCurrentTetromino() {
        if (currentTetromino != null) {
            currentTetromino.moveDown();
            if (currentTetromino.coincide()) {

                int linesCleared = wall.add(currentTetromino);
                if (linesCleared > 0) {
                    scoreManager.updateScore(linesCleared, 0); // 更新分數
                }
                holdUsed = false;
                spawnNewTetromino();
            }
            repaint();
        }
    }

    public void moveCurrentTetrominoLeft() {
        if (currentTetromino != null) {
            currentTetromino.moveLeft();
            repaint();
        }
    }
    