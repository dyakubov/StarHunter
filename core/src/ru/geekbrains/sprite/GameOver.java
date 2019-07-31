package ru.geekbrains.sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.screen.GameScreen;


public class GameOver extends Sprite {
    private Vector2 v = new Vector2();
    private ButtonNewGame buttonNewGame;

    public GameOver(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("message_game_over"));
        pos.set(0, 0.5f);
        v.set(0, -0.1f);
        buttonNewGame = new ButtonNewGame(atlas, gameScreen);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        buttonNewGame.resize(worldBounds);
        buttonNewGame.setBottom(worldBounds.getBottom() + 0.3f);
        setSize(worldBounds.getWidth() - 0.1f, worldBounds.getHeight()*0.1f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        buttonNewGame.update(delta);
        if (pos.y > 0){
            pos.mulAdd(v, delta);
        }
    }

    public boolean touchDown(Vector2 touch, int pointer, int button) {
        buttonNewGame.touchDown(touch, pointer, button);
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer, int button) {
        buttonNewGame.touchUp(touch, pointer, button);
        return false;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        buttonNewGame.draw(batch);
    }
}
