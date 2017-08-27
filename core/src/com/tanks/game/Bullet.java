package com.tanks.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by majkic on 21.2.17..
 */

public class Bullet extends OnScreenObject {

    public static final int TYPE_0 = 0;
    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;

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

    public void setCurrentType(int weaponType){
        switch (weaponType) {
            case TYPE_0:
                setTexture(new Texture("bullet.png"));
                break;
            case TYPE_1:
                setTexture(new Texture("blueBullet.png"));
                break;
            case TYPE_2:
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

}
