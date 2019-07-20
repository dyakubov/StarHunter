package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;
import ru.geekbrains.objects.SpaceCraft;

public class MenuScreen extends BaseScreen {

    private Texture background;
    private Texture spaceshipPic;
    private Vector2 v; //Initial speed
    private Vector2 pos; //Spaceship current position
    private Vector2 buf;
    private SpaceCraft spaceCraft;

    private static final float V_LEN = 0.005f;

    @Override
    public void show() {
        super.show();
        background = new Texture("background.png");
        spaceshipPic = new Texture("spaceship.png");
        v = new Vector2();
        pos = new Vector2();
        buf = new Vector2();
        spaceCraft = new SpaceCraft(spaceshipPic);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        buf.set(touch);
        if (buf.sub(spaceCraft.getX(), spaceCraft.getY()).len() > V_LEN) {
            spaceCraft.setPosition(spaceCraft.getX() + v.x, spaceCraft.getY() + v.y);
        } else{
            spaceCraft.setPosition(touch.x, touch.y);
            v.setZero();
        }
        Gdx.gl.glClearColor(0.26f, 0.5f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, -1, -1, 2,2);
        batch.draw(spaceCraft.getTexture(), spaceCraft.getX(), spaceCraft.getY(), 0.2f,0.2f);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        spaceshipPic.dispose();
    }

//    @Override
//    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        touchPos.set(screenX, Gdx.graphics.getHeight() - screenY);
//        v.set(touchPos.cpy().sub(pos)).setLength(V_LEN);
//        return false;
//    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        v.set(touch.cpy().sub(spaceCraft.getX(), spaceCraft.getY())).setLength(V_LEN);
        return super.touchUp(screenX, screenY, pointer, button);
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

