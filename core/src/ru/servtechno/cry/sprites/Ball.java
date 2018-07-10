package ru.servtechno.cry.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Ball {

    private static final int MOVEMENT = 100;
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;

    private Texture ball;

    public Ball(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        ball = new Texture("ball.png");
        bounds = new Rectangle(position.x, position.y, ball.getWidth(), ball.getHeight());
    }

    public void update(float dt){
        velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);

        velocity.scl(1 / dt);

        if(position.y < 75){
            position.y = 75;
        }

        if(position.y > 525 - ball.getHeight()){
            position.y = 525 - ball.getHeight();
        }

        bounds.setPosition(position.x, position.y);
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void jump(){
        velocity.y = 300;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBall() {
        return ball;
    }

    public void dispose() {
        ball.dispose();
    }
}
