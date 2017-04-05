package com.tanks.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by majkic on 17.2.17..
 */

public class GameStage extends Stage {

    private OnScreenObject land;
    private OnScreenObject sky;
    private OnScreenObject tank1;
    private OnScreenObject tank2;
    private OnScreenObject bullet1;
    private OnScreenObject bullet2;
    private OnScreenObject leftArrow;
    private OnScreenObject rightArrow;

    public GameStage() {

        land = new OnScreenObject();
        land.setTexture(new Texture("land.jpg"));
        land.setVisible(true);
        land.setWidth(Gdx.graphics.getWidth());
        land.setHeight(Gdx.graphics.getHeight() / 3);
        land.setPosition(0, 0);

        sky = new OnScreenObject();
        sky.setTexture(new Texture("sky.jpg"));
        sky.setVisible(true);
        sky.setWidth(Gdx.graphics.getWidth());
        sky.setHeight(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 3);
        sky.setPosition(0, Gdx.graphics.getHeight() / 3);

        tank1 = new OnScreenObject();
        tank1.setTexture(new Texture("tank1.png"));
        tank1.setVisible(true);
        tank1.setWidth(80);
        tank1.setHeight(62);
        tank1.setPosition(40, Gdx.graphics.getHeight() / 3);

        tank2 = new OnScreenObject();
        tank2.setTexture(new Texture("tank2.png"));
        tank2.setVisible(true);
        tank2.setWidth(80);
        tank2.setHeight(62);
        tank2.setPosition(Gdx.graphics.getWidth() - tank2.getWidth() - 40, Gdx.graphics.getHeight() / 3);


        bullet1 = new Bullet();
        bullet1.setTexture(new Texture("bullet.png"));
        bullet1.setVisible(false);
        bullet1.setWidth(15);
        bullet1.setHeight(15);
        bullet1.setPosition(tank1.getX() + tank1.getWidth(), tank1.getY() + tank1.getHeight());

        bullet2 = new Bullet();
        bullet2.setTexture(new Texture("bullet.png"));
        bullet2.setVisible(false);
        bullet2.setWidth(15);
        bullet2.setHeight(15);
        bullet2.setPosition(tank2.getX() - bullet2.getWidth(), tank2.getY() + tank2.getHeight());

        leftArrow = new OnScreenObject();
        leftArrow.setTexture(new Texture("leftArrow.png"));
        leftArrow.setVisible(true);
        leftArrow.setWidth(60);
        leftArrow.setHeight(60);
        leftArrow.setPosition(30, 30);
        leftArrow.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("Left arrow clicked");
            }
        });

        rightArrow = new OnScreenObject();
        rightArrow.setTexture(new Texture("rightArrow.png"));
        rightArrow.setVisible(true);
        rightArrow.setWidth(60);
        rightArrow.setHeight(60);
        rightArrow.setPosition(120, 30);

        addActor(land);
        addActor(sky);
        addActor(tank1);
        addActor(tank2);
        addActor(bullet1);
        addActor(bullet2);
        addActor(leftArrow);
        addActor(rightArrow);
    }

    public void update1(){
        bullet1.setPosition(tank1.getX() + tank1.getWidth(), tank1.getY() + tank1.getHeight());
    }

    public void update2(){
        bullet2.setPosition(tank1.getX() + tank1.getWidth(), tank1.getY() + tank1.getHeight());
    }

    public OnScreenObject getTank1() {
        return tank1;
    }

    public void setTank1(OnScreenObject tank1) {
        this.tank1 = tank1;
    }

    public OnScreenObject getTank2() {
        return tank2;
    }

    public void setTank2(OnScreenObject tank2) {
        this.tank2 = tank2;
    }

    public OnScreenObject getBullet1() {
        return bullet1;
    }

    public void setBullet1(OnScreenObject bullet1) {
        this.bullet1 = bullet1;
    }

    public OnScreenObject getBullet2() {
        return bullet2;
    }

    public void setBullet2(OnScreenObject bullet2) {
        this.bullet2 = bullet2;
    }

    public OnScreenObject getLeftArrow() {
        return leftArrow;
    }

    public void setLeftArrow(OnScreenObject leftArrow) {
        this.leftArrow = leftArrow;
    }

    public OnScreenObject getRightArrow() {
        return rightArrow;
    }

    public void setRightArrow(OnScreenObject rightArrow) {
        this.rightArrow = rightArrow;
    }

    public OnScreenObject getLand() {
        return land;
    }

    public void setLand(OnScreenObject land) {
        this.land = land;
    }

    public OnScreenObject getSky() {
        return sky;
    }

    public void setSky(OnScreenObject sky) {
        this.sky = sky;
    }
}
