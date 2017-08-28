package com.tanks.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by majkic on 21.2.17..
 */

public class Bullet extends OnScreenObject {

    public static final int TYPE_0 = 0;
    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;

    public static final int TYPE_0_INITIAL_COUNT = 5;
    public static final int TYPE_1_INITIAL_COUNT = 3;
    public static final int TYPE_2_INITIAL_COUNT = 2;

    private int bulletType0Count = TYPE_0_INITIAL_COUNT;
    private int bulletType1Count = TYPE_1_INITIAL_COUNT;
    private int bulletType2Count = TYPE_2_INITIAL_COUNT;

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

    public int getBulletType0Count() {
        return bulletType0Count;
    }

    public void setBulletType0Count(int bulletType0Count) {
        this.bulletType0Count = bulletType0Count;
    }

    public int getBulletType1Count() {
        return bulletType1Count;
    }

    public void setBulletType1Count(int bulletType1Count) {
        this.bulletType1Count = bulletType1Count;
    }

    public int getBulletType2Count() {
        return bulletType2Count;
    }

    public void setBulletType2Count(int bulletType2Count) {
        this.bulletType2Count = bulletType2Count;
    }

    public int getCurrentTypeAmmo(){
        int retVal = bulletType0Count;
        switch (currentType) {
            case TYPE_0:
                retVal = bulletType0Count;
                break;
            case TYPE_1:
                retVal = bulletType1Count;
                break;
            case TYPE_2:
                retVal = bulletType2Count;
                break;
        }
        return retVal;
    }
}
