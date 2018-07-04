package ru.servtechno.cry.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.servtechno.cry.CryGame;

public class MenuState extends State {

    private Texture background;
    private Texture btnPlay;
    private Texture btnCalibrate;
    private Texture btnExit;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("menu-bg.jpg");
        btnPlay = new Texture("btn-play.png");
        btnCalibrate = new Texture("btn-calibrate.png");
        btnExit = new Texture("btn-exit.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();

        sb.draw(background, 0,0, CryGame.WIDTH, CryGame.HEIGHT);
        sb.draw(btnPlay, 381,300, 333, 89);
        sb.draw(btnCalibrate, 381,200, 333, 87);
        sb.draw(btnExit, 380,100, 334, 87);

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        btnPlay.dispose();
        btnCalibrate.dispose();
        btnExit.dispose();
    }
}
