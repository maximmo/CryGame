package ru.servtechno.cry.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Stone {

    private static final int FLUCTUATION_Y = 350;
    public static final int STONE_WIDTH = 70;
    public static final int TOP_BORDER = 75;
    private static final int BOTTOM_BORDER = 75;

    private Texture stone;
    private Vector2 posStone;
    private Random rand;
    private Rectangle bounds;

    public Stone(float x){
        stone = new Texture("stone.jpg");

        rand = new Random();

        posStone = new Vector2(x, BOTTOM_BORDER - stone.getHeight() + rand.nextInt(FLUCTUATION_Y));

        bounds = new Rectangle(posStone.x, posStone.y, stone.getWidth(), stone.getHeight());
    }

    public Texture getStone() {
        return stone;
    }

    public Vector2 getPosStone() {
        return posStone;
    }

    public boolean collides(Rectangle player){
        return player.overlaps(bounds);
    }

    public void dispose() {
        stone.dispose();
    }
}
