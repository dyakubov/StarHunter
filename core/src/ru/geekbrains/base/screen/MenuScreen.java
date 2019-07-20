package ru.geekbrains.base.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;

public class MenuScreen extends BaseScreen {


    private Texture background;
    private Texture spaceshipPic;
    private Vector2 touchPos; //Touch position
    private Vector2 v; //Initial speed
    private Vector2 pos; //Spaceship current position
    private Vector2 buf;

    private static final float V_LEN = 4f;



    @Override
    public void show() {
        super.show();
        background = new Texture("background.png");
        spaceshipPic = new Texture("spaceship.png");
        touchPos = new Vector2();
        v = new Vector2();
        pos = new Vector2();
        buf = new Vector2();


    }

    @Override
    public void render(float delta) {
        super.render(delta);
        buf.set(touchPos);
        if (buf.sub(pos).len() > V_LEN) {
            pos.add(v);
        } else{
            pos.set(touchPos);
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(spaceshipPic, pos.x, pos.y, 100, 100);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        background.dispose();
        spaceshipPic.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchPos.set(screenX, Gdx.graphics.getHeight() - screenY);
        v.set(touchPos.cpy().sub(pos)).setLength(V_LEN);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        setDirectionByKey(keycode);
        return false;
    }

    private void setDirectionByKey(int keycode){
        switch (keycode){
            //Up
            case 19: v.set(v.cpy().scl(0,1));
            break;

            //Down
            case 20: v.set(v.cpy().scl(0, -1));
            break;

            //Left
            case 21: v.set(v.cpy().scl(-1, 0));
            break;

            //Right
            case 22: v.set(v.cpy().scl(1, 0));
            break;

            //Space
            case 62: pos.set(0,0);
            v.set(0, 0);
            break;

            default:
                break;
        }
    }
}
