package ru.servtechno.cry.states;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.servtechno.cry.CryGame;

public class ResultState extends State {

    private Texture resultBg;

    BitmapFont font;
    String resultText;

    public ResultState(GameStateManager gsm) {
        super(gsm);
        resultBg = new Texture("result-2.jpg");
        camera.setToOrtho(false, CryGame.WIDTH, CryGame.HEIGHT);


        font = new BitmapFont(Gdx.files.getFileHandle("fonts/GalponBTM.fnt", Files.FileType.Internal));
        font.setColor(Color.BLACK);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
            //CryGame.micMonitoring.startCapturing();
            gsm.set(new PlayState(gsm));
    }

    @Override
    public void update(float dt) {
        camera.position.x = 0;
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();

        sb.draw(resultBg, 0,0, CryGame.WIDTH, CryGame.HEIGHT);
        //font.draw(sb, resultText, CryGame.WIDTH/2, CryGame.HEIGHT/2);
        font.draw(sb, "You result", CryGame.WIDTH/2-80, CryGame.HEIGHT/2 + 25);
        font.draw(sb, resultText, CryGame.WIDTH/2-80, CryGame.HEIGHT/2 - 5);

        sb.end();
    }

    @Override
    public void dispose() {
        resultBg.dispose();
        font.dispose();
    }

    public void setResultText(String resultText) {
        this.resultText = resultText;
    }
}
