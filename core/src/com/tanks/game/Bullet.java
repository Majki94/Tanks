package com.tanks.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by majkic on 21.2.17..
 */

public class Bullet extends OnScreenObject {

    public static int TYPE_0 = 0;
    public static int TYPE_1 = 1;
    public static int TYPE_2 = 2;

    float elapsedTime = 0;
    private double halfTime;
    private final double G = 9.81;
    private int currentType = 0;


    @Override
    public void act(float dt) {
        if(started){
            elapsedTime += dt;
            int angle = 45;
            moveBy((float)(brzinaX * elapsedTime * Math.cos(angle)), (float)(brzinaY * elapsedTime * Math.sin(angle) - 0.5 * G * elapsedTime * elapsedTime));
        }
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        return super.hit(x, y, touchable);
    }

    public void setType(int weaponType){
        switch (weaponType) {
            case 0:
                setTexture(new Texture("bullet.png"));
                break;
            case 1:
                setTexture(new Texture("blueBullet.png"));
                break;
            case 2:
                setTexture(new Texture("redBullet.png"));
                break;
        }
        setWidth(GameStage.BULLET_SIZE);
        setHeight(GameStage.BULLET_SIZE);
        currentType = weaponType;
    }

    public int getCurrentType() {
        return currentType;
    }

    public void setCurrentType(int currentType) {
        this.currentType = currentType;
    }
}
