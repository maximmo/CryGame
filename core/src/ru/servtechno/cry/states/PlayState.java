package ru.servtechno.cry.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.servtechno.cry.CryGame;
import ru.servtechno.cry.sprites.Ball;

public class PlayState extends State {

    private Ball ball;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        ball = new Ball(100, 75);
        camera.setToOrtho(false, CryGame.WIDTH, CryGame.HEIGHT);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        ball.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(ball.getBall(), ball.getPosition().x, ball.getPosition().y, 64, 64);
        sb.end();
    }

    @Override
    public void dispose() {
        ball.getBall().dispose();
    }
}
