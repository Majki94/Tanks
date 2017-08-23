package com.tanks.game;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by majkic on 21.2.17..
 */

public class Bullet extends OnScreenObject {

    float elapsedTime = 0;
    private double halfTime;
    private final double G = 9.81;


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
}
