package ru.geekbrains.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.math.Rect;

public abstract class ShipControls extends Sprite {
    private Rect worldBounds;
    private static final float BOTTOM_INDENT = -0.35f;
    private static final float PLAYER_SPEED = 0.01f;
    private static final float PLAYER_Y_POS = 0f;

    private int currentKey = 0;

    public ShipControls(TextureRegion region) {
        super(region);
        pos.set(0, BOTTOM_INDENT);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.1f);
        this.worldBounds = worldBounds;
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        if (isMe(touch)){
            pos.set(touch.x, BOTTOM_INDENT);
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        currentKey = keycode;
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        currentKey = 0;
        return false;
    }

    @Override
    public void update(float delta) {
        checkDirection();
        checkBounds();
    }

    private void checkBounds() {
        if (getRight() < worldBounds.getLeft()) pos.set(getRight(), BOTTOM_INDENT);
        if (getLeft() > worldBounds.getRight()) pos.set(getLeft(), BOTTOM_INDENT);
    }

    private void checkDirection(){
        if (currentKey > 0) {
            if (currentKey == 21) {
                pos.add(-PLAYER_SPEED, PLAYER_Y_POS);
            } else if (currentKey == 22) {
                pos.add(PLAYER_SPEED, PLAYER_Y_POS);
            }
        }
    }
}
