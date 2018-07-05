package ru.servtechno.cry.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Stone {

    private static final int FLUCTUATION_Y = 300;
    //public static final int STONE_GAP = 100;
    //public static final int LOWEST_OPENING = 100;
    public static final int STONE_WIDTH = 70;

    private Texture stone;
    private Vector2 posStone;
    private Random rand;

    public Stone(float x){
        stone = new Texture("stone.jpg");

        rand = new Random();

        posStone = new Vector2(x, rand.nextInt(FLUCTUATION_Y));
    }

    public Texture getStone() {
        return stone;
    }

    public Vector2 getPosStone() {
        return posStone;
    }

}
