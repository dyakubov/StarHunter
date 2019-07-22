package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.base.ShipControls;

public class Player extends ShipControls {

    public Player(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"));
    }








}
