package com.tanks.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame implements ApplicationListener, GestureDetector.GestureListener {

    private Stage mainStage;
    private OnScreenObject land;
    private OnScreenObject sky;
    private OnScreenObject tank1;
    private OnScreenObject bullet1;
    private OnScreenObject tank2;
    private OnScreenObject bullet2;
    private OnScreenObject leftArrow;
    private OnScreenObject rightArrow;
    private float dt;
    private GestureDetector gd;
    private float scale;
    private OrthographicCamera camera;
    private Viewport vp;
    private final double ZOOM_SPEED = 0.004;
    private int turn = 0;

    @Override
    public void create() {

        scale = (float) (Gdx.graphics.getWidth() * 0.000004167);

        mainStage = new GameStage();

        land = ((GameStage) mainStage).getLand();
        sky = ((GameStage) mainStage).getSky();
        tank1 = ((GameStage) mainStage).getTank1();
        bullet1 = ((GameStage) mainStage).getBullet1();
        tank2 = ((GameStage) mainStage).getTank2();
        bullet2 = ((GameStage) mainStage).getBullet2();

        leftArrow = ((GameStage) mainStage).getLeftArrow();
//        leftArrow.addListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                super.clicked(event, x, y);
//                System.out.println("Click event");
//                System.out.println("Tank 1 X : " + tank1.getX());
//                if (turn % 2 == 0) {
//                    tank1.started = true;
//                    tank1.moveBy(50, 0);
//                    tank1.started = false;
//                } else {
//                    tank2.started = true;
//                    tank2.moveBy(50, 0);
//                    tank2.started = false;
//                }
//                System.out.println("Tank 1 X : " + tank1.getX());
//            }
//        });
        rightArrow = ((GameStage) mainStage).getRightArrow();

        gd = new GestureDetector(this);
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        vp = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

        mainStage.setViewport(vp);

    }

    @Override
    public void render() {

        //nova raketa
        if (bullet1.getY() < land.getHeight()) {
            bullet1.setVisible(false);
            bullet1.setPosition(tank1.getX() + tank1.getWidth(), tank1.getY() + tank1.getHeight());
            bullet1.started = false;
            ((Bullet) bullet1).elapsedTime = 0;
            bullet1.brzinaX = 0;
            bullet1.brzinaY = 0;
        }
        if (bullet2.getY() < land.getHeight()) {
            bullet2.setVisible(false);
            bullet2.setPosition(tank2.getX() - bullet2.getWidth(), tank2.getY() + tank2.getHeight());
            bullet2.started = false;
            ((Bullet) bullet2).elapsedTime = 0;
            bullet2.brzinaX = 0;
            bullet2.brzinaY = 0;
        }

        tank1.setPosition(tank1.getX(), tank1.getY());

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
        if (turn % 2 == 0 && !bullet1.isVisible() && !bullet2.isVisible()) {
            //prvi igrac igra
            bullet1.setVisible(true);
            bullet1.started = true;
            bullet1.brzinaX = velocityX * scale;
            bullet1.brzinaY = -velocityY * scale;
            turn++;
        } else if (!bullet1.isVisible() && !bullet2.isVisible()) {
            //drugi igrac igra
            bullet2.setVisible(true);
            bullet2.started = true;
            bullet2.brzinaX = velocityX * scale;
            bullet2.brzinaY = -velocityY * scale;
            turn++;
        }

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
