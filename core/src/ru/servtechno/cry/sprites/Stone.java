package ru.servtechno.cry.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Stone {

    public static final int FLUCTUATION_Y = 100;
    public static final int STONE_GAP = 100;
    public static final int STONE_WIDTH = 70;
    public static final int TOP_BORDER = 75;
    public static final int BOTTOM_BORDER = 75;

    private Texture stone;
    private Vector2 posStone;
    private Random rand;

    public Stone(float x){
        stone = new Texture("stone.jpg");

        rand = new Random();

        posStone = new Vector2(x, rand.nextInt(FLUCTUATION_Y) - STONE_GAP + BOTTOM_BORDER);
    }

    public Texture getStone() {
        return stone;
    }

    public Vector2 getPosStone() {
        return posStone;
    }

}
