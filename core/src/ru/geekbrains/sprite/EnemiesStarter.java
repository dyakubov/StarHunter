package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;
import ru.geekbrains.pool.EnemyPool;
import ru.geekbrains.utils.Regions;

public class EnemiesStarter extends Sprite{
    private Rect worldBounds;
    private float enemiesInterval;
    private float timer;
    private EnemyPool enemyPool;

    private Vector2 pos0;
    private Vector2 v;

    private TextureRegion[] regions;

    private TextureRegion enemyRegion;

    public EnemiesStarter(TextureAtlas atlas, EnemyPool enemyPool){
        this.enemyPool = enemyPool;
        this.regions = Regions.split(atlas.findRegion("enemy0"), 1,2,2);
        this.enemyRegion = regions[0];
        this.enemiesInterval = Rnd.nextFloat(1, 3);
        this.pos0 = new Vector2(0, 0.5f);
        this.v = new Vector2(0, -0.1f);

    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta){
        timer += delta;
        if (timer >= enemiesInterval) {
            timer = 0f;
            launchEnemy();
        }
    }

    public void launchEnemy(){
        Enemy enemy = enemyPool.obtain();
        pos0.set(Rnd.nextFloat(-0.5f, 0.5f), 0.5f);
        enemy.set(enemyRegion, pos0, v, 0.1f, worldBounds);
    }
}
