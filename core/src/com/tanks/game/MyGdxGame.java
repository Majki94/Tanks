package com.tanks.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MyGdxGame implements ApplicationListener, GestureDetector.GestureListener {

    private Stage mainStage;
    private OnScreenObject tank;
    private OnScreenObject rocket;
    private float dt;

    @Override
    public void create() {
        mainStage = new GameStage();
        tank = ((GameStage) mainStage).getTank();
        rocket = ((GameStage) mainStage).getRocket();
    }

    @Override
    public void render() {

        //nova raketa
        if(rocket.getY() < 0){
            rocket.setVisible(false);
            rocket.setPosition(tank.getX() + tank.getWidth(), tank.getY() + tank.getHeight());
            rocket.started = false;
            ((Rocket) rocket).elapsedTime = 0;
            rocket.brzinaX = 0;
            rocket.brzinaY = 0;
        }

        dt = Gdx.graphics.getDeltaTime();
        mainStage.act(dt);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        GestureDetector gd = new GestureDetector(this);

        Gdx.input.setInputProcessor(gd);

        mainStage.draw();
    }

    @Override
    public void dispose() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        rocket.setVisible(true);
        rocket.started = true;
        rocket.brzinaX = velocityX * 30;
        rocket.brzinaY = velocityY * 30;
        System.out.print("V x = " + velocityX + "   ");
        System.out.println("V y = " + velocityY);
        System.out.print("Brzina X = " + rocket.brzinaX + "   ");
        System.out.println("Brzina Y = " + rocket.brzinaY);
        return true;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
//        tank.setPosition(x, Gdx.graphics.getHeight() - y);
//        if (mainStage instanceof GameStage){
//            ((GameStage) mainStage).update();
//        }
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }

}
