package com.PD2.Tetris.block;

import com.PD2.Tetris.shape.*;
import com.PD2.Tetris.App.Tetris;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public abstract class Tetromino {
	private Cell center;
	private int rotateTime;
	protected int[][][] stateList;

	public static Tetromino random() {
		int random = (int) (Math.random() * 7);
		switch (random) {
			case 0:
				return new I();
			case 1:
				return new J();
			case 2:
				return new L();
			case 3:
				return new O();
			case 4:
				return new S();
			case 5:
				return new Z();
			case 6:
				return new T();
			default:
				return null;
		}
	}

	public Tetromino() {
		rotateTime = 0;
		center = new Cell(5, 0);
	}

	public Tetromino(int x, int y) {
		rotateTime = 0;
		center = new Cell(x, y);
	}

	public void moveLeft() {
		center.moveLeft();
		if (coincide()) {
			center.moveRight();
		}
	}

	public int moveDown() {
		center.moveDown();

		if (coincide()) {	//他這邊吃的到coincide==true
			//System.out.println("coincide");
			center.moveUp();

			return 1;
		}
		return 0;
	}


	public void moveRight() {
		center.moveRight();
		if (coincide()) {
			center.moveLeft();
		}
	}

	public void rotate() {
		rotateTime++;
		if (coincide()) {
			rotateTime--;
		}
	}
        
public boolean coincide() {
		int[][] positions = getBlockPositions();
		for (int[] position : positions) {
			int x = position[0];
			int y = position[1];
			//System.out.println(x);
			if (x <= -1 || x >= 9 || y>=18 || y <=-1 ) {
				//System.out.println("reach buttom");
				return true;
			}
			else if(Tetris.wall.hasBlock(x, y)){
				return  true;
			}


		}


		return false;
