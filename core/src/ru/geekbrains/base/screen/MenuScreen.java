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
    private Vector2 currentV; //Current speed
    private Vector2 pos; //Spaceship current position
    private Vector2 direction; //Spaceship direction
    private float length; //Length of path from spaceship to touch position



    @Override
    public void show() {
        super.show();
        background = new Texture("background.png");
        spaceshipPic = new Texture("spaceship.png");
        touchPos = new Vector2();
        v = new Vector2(4, 4);
        currentV = new Vector2(0,0);
        pos = new Vector2();
        direction = new Vector2();
        length = 0;
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        pos.add(currentV);
        length = (touchPos.cpy().sub(pos)).len();

        if (length < 2){
            currentV.setZero();
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
        direction.set(touchPos.cpy().sub(pos)).nor();
        currentV.set(v.cpy().scl(direction));
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
            case 19: currentV.set(v.cpy().scl(0,1));
            break;

            //Down
            case 20: currentV.set(v.cpy().scl(0, -1));
            break;

            //Left
            case 21: currentV.set(v.cpy().scl(-1, 0));
            break;

            //Right
            case 22: currentV.set(v.cpy().scl(1, 0));
            break;

            //Space
            case 62: pos.set(0,0);
            currentV.set(0, 0);
            break;

            default:
                break;
        }
    }
}
