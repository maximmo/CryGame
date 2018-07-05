package ru.servtechno.cry.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

import ru.servtechno.cry.CryGame;
import ru.servtechno.cry.sprites.Ball;
import ru.servtechno.cry.sprites.Stone;

public class PlayState extends State {

    private Random randX;
    private Ball ball;
    private Texture bgGame;

    public static final int FLUCTUATION_X = 450;
    public static final int MIN_SPACING = 100;
    public static final int STONE_COUNT = 30;

    private Array<Stone> stones;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        ball = new Ball(100, 300);
        bgGame = new Texture("bg-game.jpg");
        camera.setToOrtho(false, CryGame.WIDTH, CryGame.HEIGHT);

        stones = new Array<Stone>();

        randX = new Random();

        float oldX = 300;
        for(int i=1; i<=STONE_COUNT; i++){
            Stone stone = new Stone(oldX + MIN_SPACING + Stone.STONE_WIDTH + randX.nextInt(FLUCTUATION_X));
            stones.add(stone);
            oldX = stone.getPosStone().x;
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
        ball.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        ball.update(dt);
        camera.position.x = ball.getPosition().x + 100;
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bgGame, camera.position.x - (camera.viewportWidth/2), 76);
        sb.draw(ball.getBall(), ball.getPosition().x, ball.getPosition().y, 64, 64);
        for(Stone stone : stones){
            sb.draw(stone.getStone(), stone.getPosStone().x, stone.getPosStone().y);
        }

        sb.end();
    }

    @Override
    public void dispose() {
        ball.getBall().dispose();
    }
}
