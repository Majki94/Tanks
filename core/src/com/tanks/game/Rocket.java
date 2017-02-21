package com.tanks.game;

/**
 * Created by majkic on 21.2.17..
 */

public class Rocket extends OnScreenObject {

    float elapsedTime = 0;

    @Override
    public void act(float dt) {
        if(started){
            elapsedTime += dt;
            int angle = 45;
            moveBy((float)(brzinaX * elapsedTime * Math.cos(angle)), (float)(brzinaY * elapsedTime * Math.sin(angle) - 0.5 * 9.81 * elapsedTime * elapsedTime));
        }
    }
}
