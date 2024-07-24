package com.PD2.Tetris.block;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Wall {
    private static final int WIDTH = 9;
    private static final int HEIGHT = 19;
    private final BufferedImage[][] wall;

    public static final int FAIL = -1;
    public static final int LOSE = -2;

    public Wall() {
        wall = new BufferedImage[HEIGHT][WIDTH];
    }

    public boolean hasBlock(int x, int y) {
        return wall[y][x] != null;
    }

    public boolean isFull(int y) {
        for (BufferedImage image : wall[y]) {
            if (image == null) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty(int y) {
        for (BufferedImage image : wall[y]) {
            if (image != null) {
                return false;
            }
        }
        return true;
    }
