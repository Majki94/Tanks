package com.tanks.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by majkic on 17.2.17..
 */

public class GameStage extends Stage {

    private OnScreenObject tank;
    private OnScreenObject rocket;

    public GameStage() {
        tank = new OnScreenObject();
        tank.setTexture(new Texture("tank1.png"));
        tank.setVisible(true);
        tank.setWidth(80);
        tank.setHeight(62);
//        tank.setPosition(Gdx.graphics.getWidth() / 2 - tank.getWidth() / 2, Gdx.graphics.getHeight() / 2 - tank.getHeight() / 2);
        tank.setPosition(40, Gdx.graphics.getHeight() / 2 - tank.getHeight() / 2);


        rocket = new Rocket();
        rocket.setTexture(new Texture("rocket1.png"));
        rocket.setVisible(false);
        rocket.setWidth(15);
        rocket.setHeight(15);
        rocket.setPosition(tank.getX() + tank.getWidth(), tank.getY() + tank.getHeight());

        addActor(rocket);
        addActor(tank);
    }

    public void update(){
        rocket.setPosition(tank.getX() + tank.getWidth(), tank.getY() + tank.getHeight());
    }

    public OnScreenObject getTank() {
        return tank;
    }

    public void setTank(OnScreenObject tank) {
        this.tank = tank;
    }

    public OnScreenObject getRocket() {
        return rocket;
    }

    public void setRocket(OnScreenObject rocket) {
        this.rocket = rocket;
    }
}
