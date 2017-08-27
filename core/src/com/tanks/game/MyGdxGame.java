package com.tanks.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame implements ApplicationListener, GestureDetector.GestureListener {

    private InputMultiplexer multiplexer;
    private Stage mainStage;
    private OnScreenObject land;
    private OnScreenObject sky;
    private OnScreenObject tank1;
    private OnScreenObject bullet1;
    private OnScreenObject tank2;
    private OnScreenObject bullet2;
    private OnScreenObject leftArrow;
    private OnScreenObject rightArrow;
    private OnScreenObject player1StringImage;
    private OnScreenObject player2StringImage;
    private Label player1ScoreLabel;
    private Label player2ScoreLabel;
    private int player1Score;
    private int player2Score;
    private float dt;
    private GestureDetector gd;
    private float scale;
    private OrthographicCamera camera;
    private Viewport vp;
    private final double ZOOM_SPEED = 0.004;
    private int turn = 0;
    private static int MOVE_LENGTH = 5;
    private boolean leftArrowClicked = false;
    private boolean rightArrowClicked = false;
    private OnScreenObject weaponPicker1;
    private OnScreenObject weaponPicker2;
    private OnScreenObject weaponPicker3;
    private OnScreenObject weaponPickerBg;

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
        rightArrow = ((GameStage) mainStage).getRightArrow();

        player1StringImage = ((GameStage) mainStage).getPlayer1StringImage();
        player2StringImage = ((GameStage) mainStage).getPlayer2StringImage();

        player1ScoreLabel = ((GameStage) mainStage).getPlayer1ScoreLabel();
        player2ScoreLabel = ((GameStage) mainStage).getPlayer2ScoreLabel();

        weaponPickerBg = ((GameStage) mainStage).getWeaponPickerBg();
        weaponPicker1 = ((GameStage) mainStage).getWeaponPicker1();
        weaponPicker2 = ((GameStage) mainStage).getWeaponPicker2();
        weaponPicker3 = ((GameStage) mainStage).getWeaponPicker3();

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
            turn++;
        }
        if (bullet2.getY() < land.getHeight()) {
            bullet2.setVisible(false);
            bullet2.setPosition(tank2.getX() - bullet2.getWidth(), tank2.getY() + tank2.getHeight());
            bullet2.started = false;
            ((Bullet) bullet2).elapsedTime = 0;
            bullet2.brzinaX = 0;
            bullet2.brzinaY = 0;
            turn++;
        }

        if (turn % 2 == 0) {
            player1StringImage.setVisible(true);
            player2StringImage.setVisible(false);
            int type = ((Bullet) bullet1).getCurrentType();
            OnScreenObject onScreenObject = getWeaponForCurrentType(type);
            weaponPickerBg.setPosition(onScreenObject.getX(), onScreenObject.getY());
        } else {
            player1StringImage.setVisible(false);
            player2StringImage.setVisible(true);
            int type = ((Bullet) bullet2).getCurrentType();
            OnScreenObject onScreenObject = getWeaponForCurrentType(type);
            weaponPickerBg.setPosition(onScreenObject.getX(), onScreenObject.getY());
        }

        if (leftArrowClicked) {
            moveTankOnTurn(-MOVE_LENGTH);
        }
        if (rightArrowClicked) {
            moveTankOnTurn(MOVE_LENGTH);
        }

        dt = Gdx.graphics.getDeltaTime();
        mainStage.act(dt);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(mainStage);
        multiplexer.addProcessor(gd);
        Gdx.input.setInputProcessor(multiplexer);

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
//            turn++;
        } else if (!bullet1.isVisible() && !bullet2.isVisible()) {
            //drugi igrac igra
            bullet2.setVisible(true);
            bullet2.started = true;
            bullet2.brzinaX = velocityX * scale;
            bullet2.brzinaY = -velocityY * scale;
//            turn++;
        }

        return false;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        leftArrowOnClick(x, y);
        rightArrowOnClick(x, y);
        weaponOnClick(weaponPicker1, x, y, Bullet.TYPE_0);
        weaponOnClick(weaponPicker2, x, y, Bullet.TYPE_1);
        weaponOnClick(weaponPicker3, x, y, Bullet.TYPE_2);
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        leftArrowClicked = false;
        rightArrowClicked = false;
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

    private boolean inRange(float value, float rangeStart, float rangeEnd) {
        return rangeStart <= value && value <= rangeEnd;
    }

    private void leftArrowOnClick(float x, float y) {
        if (inRange(x, leftArrow.getX(), leftArrow.getX() + leftArrow.getWidth())
                && inRange(Gdx.graphics.getHeight() - y, leftArrow.getY(), leftArrow.getHeight() + leftArrow.getHeight())) {
            leftArrowClicked = true;
        }
    }

    private void rightArrowOnClick(float x, float y) {
        if (inRange(x, rightArrow.getX(), rightArrow.getX() + rightArrow.getWidth())
                && inRange(Gdx.graphics.getHeight() - y, rightArrow.getY(), rightArrow.getHeight() + rightArrow.getHeight())) {
            rightArrowClicked = true;
        }
    }

    private void moveTankOnTurn(int side) {
        if (!bullet1.isVisible() && !bullet2.isVisible()) {
            if (turn % 2 == 0) {
                tank1.moveBy(side, 0);
                bullet1.setPosition(tank1.getX() + tank1.getWidth(), tank1.getY() + tank1.getHeight());
            } else {
                tank2.moveBy(side, 0);
                bullet2.setPosition(tank2.getX() - bullet2.getWidth(), tank2.getY() + tank2.getHeight());
            }
        } else {
            leftArrowClicked = false;
            rightArrowClicked = false;
        }
    }

    private void weaponOnClick(OnScreenObject weapon, float x, float y, int weaponType) {
        if (inRange(x, weapon.getX(), weapon.getX() + weapon.getWidth()) &&
                inRange(Gdx.graphics.getHeight() - y, weapon.getY(), weapon.getHeight() + weapon.getHeight())) {
            if (turn % 2 == 0) {
                setWeapon(weapon, (Bullet) bullet1, weaponType);
            } else {
                setWeapon(weapon, (Bullet) bullet2, weaponType);
            }
        }
    }

    private void setWeapon(OnScreenObject onScreenObject, Bullet bullet, int weaponType) {
        if (!bullet.isVisible()) {
            bullet.setCurrentType(weaponType);
            weaponPickerBg.setPosition(onScreenObject.getX(), onScreenObject.getY());
            weaponPickerBg.setVisible(true);
        }
    }

    private OnScreenObject getWeaponForCurrentType(int type) {
        if (type == Bullet.TYPE_0) {
            return weaponPicker1;
        } else if (type == Bullet.TYPE_1) {
            return weaponPicker2;
        } else {
            return weaponPicker3;
        }
    }

}
