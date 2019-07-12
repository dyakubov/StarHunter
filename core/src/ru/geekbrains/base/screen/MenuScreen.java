package ru.geekbrains.base.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;

public class MenuScreen extends BaseScreen {


    private Texture background;
    private Texture spaceship;
    private Vector2 touch;
    private Vector2 v;
    private Vector2 currentV;
    private Vector2 pos;
    private Vector2 direction;
    private float length;



    @Override
    public void show() {
        super.show();
        background = new Texture("background.png");
        spaceship = new Texture("spaceship.png");
        touch = new Vector2();
        v = new Vector2(4f, 2f);
        currentV = new Vector2(0,0);
        pos = new Vector2();
        direction = new Vector2();
        length = 0f;


    }

    @Override
    public void render(float delta) {
        super.render(delta);
        length = (touch.cpy().sub(pos)).len();
        pos.add(currentV);

        if (length < 50f){
            currentV.set(0f, 0f);
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(spaceship, pos.x, pos.y);

        System.out.println(length);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        background.dispose();
        spaceship.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        direction.set(touch.sub(pos));
        direction.nor();
        currentV = v.cpy().scl(direction);
        System.out.println("Direction: " + direction.x + " " + direction.y);
        return false;
    }
}
