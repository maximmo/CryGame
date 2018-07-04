package ru.servtechno.cry.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuState extends State {

    private Texture background;
    private Texture btnPlay;
    private Texture btnCalibrate;
    private Texture btnExit;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
        btnPlay = new Texture("btnplay.png");
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

    }

    @Override
    public void dispose() {

    }
}
