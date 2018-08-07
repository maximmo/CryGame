package ru.servtechno.cry;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicPlayer {

    private Music music;

    public MusicPlayer(String path) {
        music = Gdx.audio.newMusic(Gdx.files.getFileHandle(path, Files.FileType.Internal));
    }

    public Music getMusic() {
        return music;
    }

    public void playMusic(){
        music.play();
    }

    public void stopMusic(){
        if(music.isPlaying())
            music.stop();
    }

    public float getPosition(){
        return music.getPosition();
    }

    public void dispose(){
        music.dispose();
    }
}
