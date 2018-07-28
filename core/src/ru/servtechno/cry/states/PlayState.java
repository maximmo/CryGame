package ru.servtechno.cry.states;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.AudioRecorder;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

import ru.servtechno.cry.CryGame;
import ru.servtechno.cry.sprites.Ball;
import ru.servtechno.cry.sprites.Stone;

public class PlayState extends State {

    private Random randX;
    private Ball ball;
    private Texture bgGame;
    private Texture ground;
    private Vector2 groundPos1, groundPos2, groundTopPos1, groundTopPos2;

    //private AudioDevice playbackDevice;
    //private AudioRecorder recordingDevice;
    private Music music;

    public static final int BALL_START_OFFSET = 300;
    public static final int FLUCTUATION_X = 450;
    public static final int MIN_SPACING = 100;
    public static final int STONE_COUNT = 30;

    private Array<Stone> stones;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        ball = new Ball(100, 300);
        bgGame = new Texture("bg-game.jpg");
        camera.setToOrtho(false, CryGame.WIDTH, CryGame.HEIGHT);

        ground = new Texture("ground.jpg");
        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth/2, 0);
        groundPos2 = new Vector2((camera.position.x - camera.viewportWidth/2) + ground.getWidth(), 0);
        groundTopPos1 = new Vector2(camera.position.x - camera.viewportWidth/2, bgGame.getHeight() + ground.getHeight());
        groundTopPos2 = new Vector2((camera.position.x - camera.viewportWidth/2) + ground.getWidth(), bgGame.getHeight()  + ground.getHeight());

        stones = new Array<Stone>();

        randX = new Random();

        float oldX = BALL_START_OFFSET;
        for(int i=0; i<=STONE_COUNT; i++){
            Stone stone = new Stone(oldX + MIN_SPACING + Stone.STONE_WIDTH + randX.nextInt(FLUCTUATION_X));
            stones.add(stone);
            oldX = stone.getPosStone().x;
        }

        //playbackDevice = Gdx.audio.newAudioDevice(44100, true);
        //recordingDevice = Gdx.audio.newAudioRecorder(44100, true);
        music = Gdx.audio.newMusic(Gdx.files.getFileHandle("music/EgorKrid.mp3", Files.FileType.Internal));
    }

    @Override
    protected void handleInput() {
        //здесь анализ уровня микрофона
        playMusic();

        if(Gdx.input.justTouched())
        ball.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        ball.update(dt);
        camera.position.x = ball.getPosition().x + BALL_START_OFFSET;
        for (int i = 0; i < stones.size; i++){
            Stone stone = stones.get(i);

            if(stone.collides(ball.getBounds())){
                gsm.set(new PlayState(gsm));
            }
        }
        //нарисовать финиш
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bgGame, camera.position.x - (camera.viewportWidth/2), 75);
        sb.draw(ball.getBall(), ball.getPosition().x, ball.getPosition().y, 64, 64);
        for(Stone stone : stones){
            sb.draw(stone.getStone(), stone.getPosStone().x, stone.getPosStone().y);
        }

        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.draw(ground, groundTopPos1.x, groundTopPos1.y);
        sb.draw(ground, groundTopPos2.x, groundTopPos2.y);

        sb.end();
    }

    @Override
    public void dispose() {
        bgGame.dispose();
        ground.dispose();
        ball.dispose();
        for(Stone stone : stones){
            stone.dispose();
        }

        if(music.isPlaying())
            music.stop();
        music.dispose();
        //recordingDevice.dispose();
        //playbackDevice.dispose();
    }

    private void updateGround(){
        if(camera.position.x - (camera.viewportWidth/2) > groundPos1.x + ground.getWidth()){
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if(camera.position.x - (camera.viewportWidth/2) > groundPos2.x + ground.getWidth()){
            groundPos2.add(ground.getWidth() * 2, 0);
        }
        if(camera.position.x - (camera.viewportWidth/2) > groundTopPos1.x + ground.getWidth()){
            groundTopPos1.add(ground.getWidth() * 2, 0);
        }
        if(camera.position.x - (camera.viewportWidth/2) > groundTopPos2.x + ground.getWidth()){
            groundTopPos2.add(ground.getWidth() * 2, 0);
        }
    }

    public void playMusic(){
        music.setVolume(0.5f);
        music.play();

//        AudioRecorder recordingDevice = Gdx.audio.newAudioRecorder(44100, true);
//
//        short[] samples = new short[44100*10];
//        recordingDevice.read(samples, 0 , samples.length);
//        recordingDevice.dispose();


        //playbackDevice.writeSamples(samples, 0, samples.length);
   }
}
