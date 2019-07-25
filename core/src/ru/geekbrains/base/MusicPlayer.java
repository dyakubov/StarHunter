package ru.geekbrains.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class MusicPlayer {
    private static final Music music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
    private static final Sound shoot = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));

    public static void playBattleMusic() {
        music.play();
    }

    public static void stopBattleMusic() {
        music.stop();
    }

    public static void playMainShipShootSound(){
        shoot.play((float) 0.02);
    }

    public static void dispose(){
        music.dispose();
    }
}
