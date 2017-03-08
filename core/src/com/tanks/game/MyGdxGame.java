package com.tanks.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame implements ApplicationListener, GestureDetector.GestureListener {

    private Stage mainStage;
    private OnScreenObject tank;
    private OnScreenObject rocket;
    private float dt;
    private GestureDetector gd;
    private float scale;
    private OrthographicCamera camera;
    private Viewport vp;
    private final double ZOOM_SPEED = 0.004;

    @Override
    public void create() {
        mainStage = new GameStage();

        tank = ((GameStage) mainStage).getTank();
        rocket = ((GameStage) mainStage).getRocket();

        gd = new GestureDetector(this);
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        vp = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

        mainStage.setViewport(vp);

        scale = (float) (Gdx.graphics.getWidth() * 0.000004167);
    }

    @Override
    public void render() {

        //nova raketa
        if (rocket.getY() < 0) {
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
        rocket.brzinaX = velocityX * scale;
        rocket.brzinaY = -velocityY * scale;
//        System.out.print("V x = " + velocityX + "   ");
//        System.out.println("V y = " + velocityY);
//        System.out.print("Brzina X = " + rocket.brzinaX + "   ");
//        System.out.println("Brzina Y = " + rocket.brzinaY);
        System.out.println("FLING!!!!!!");
        return false;
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
        if (initialDistance < distance) {
            camera.zoom *= (1 - ZOOM_SPEED);
        } else {
            camera.zoom *= (1 + ZOOM_SPEED);
        }
        System.out.println("ZOOOOOOM!!!!!!");
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
