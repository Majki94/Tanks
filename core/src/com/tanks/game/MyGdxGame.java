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
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame implements ApplicationListener, GestureDetector.GestureListener {

    private static final int MAX_ROUNDS = 10;

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
    private Label roundLabel;
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
    private OnScreenObject restart;
    private int round;
    private boolean gameFinished;

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
        roundLabel = ((GameStage) mainStage).getRoundLabel();

        weaponPickerBg = ((GameStage) mainStage).getWeaponPickerBg();
        weaponPicker1 = ((GameStage) mainStage).getWeaponPicker1();
        weaponPicker2 = ((GameStage) mainStage).getWeaponPicker2();
        weaponPicker3 = ((GameStage) mainStage).getWeaponPicker3();

        restart = ((GameStage) mainStage).getRestart();

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
            reloadBullet1();
        }
        if (bullet2.getY() < land.getHeight()) {
            reloadBullet2();
        }

        if (turn % 2 == 0) {
            player1StringImage.setVisible(true);
            player2StringImage.setVisible(false);
            int type = ((Bullet) bullet1).getCurrentType();
            OnScreenObject onScreenObject = getWeaponForCurrentType(type);
            weaponPickerBg.setPosition(onScreenObject.getX(), onScreenObject.getY());
            ((GameStage) mainStage).getType0CountLabel().setText("" + ((Bullet) bullet1).getBulletType0Count());
            ((GameStage) mainStage).getType1CountLabel().setText("" + ((Bullet) bullet1).getBulletType1Count());
            ((GameStage) mainStage).getType2CountLabel().setText("" + ((Bullet) bullet1).getBulletType2Count());
        } else {
            player1StringImage.setVisible(false);
            player2StringImage.setVisible(true);
            int type = ((Bullet) bullet2).getCurrentType();
            OnScreenObject onScreenObject = getWeaponForCurrentType(type);
            weaponPickerBg.setPosition(onScreenObject.getX(), onScreenObject.getY());
            ((GameStage) mainStage).getType0CountLabel().setText("" + ((Bullet) bullet2).getBulletType0Count());
            ((GameStage) mainStage).getType1CountLabel().setText("" + ((Bullet) bullet2).getBulletType1Count());
            ((GameStage) mainStage).getType2CountLabel().setText("" + ((Bullet) bullet2).getBulletType2Count());
        }

        round = turn / 2 + 1;
        if (round > MAX_ROUNDS) {
            endGame();
        }
        if (round < MAX_ROUNDS) {
            roundLabel.setText("Round : " + round);
        } else {
            roundLabel.setText("Round : " + MAX_ROUNDS);
        }

        if (leftArrowClicked) {
            moveTankOnTurn(-MOVE_LENGTH);
        }
        if (rightArrowClicked) {
            moveTankOnTurn(MOVE_LENGTH);
        }

        if (bulletHitTank(bullet1, tank2)) {
            increaseScore(bullet1, 1);
            player1ScoreLabel.setText("" + player1Score);
            reloadBullet1();
        }
        if (bulletHitTank(bullet2, tank1)) {
            increaseScore(bullet2, 2);
            player2ScoreLabel.setText("" + player2Score);
            reloadBullet2();
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

    private void reloadBullet1() {
        bullet1.setVisible(false);
        bullet1.setPosition(tank1.getX() + tank1.getWidth(), tank1.getY() + tank1.getHeight());
        bullet1.started = false;
        ((Bullet) bullet1).elapsedTime = 0;
        bullet1.brzinaX = 0;
        bullet1.brzinaY = 0;
        turn++;
    }

    private void reloadBullet2() {
        bullet2.setVisible(false);
        bullet2.setPosition(tank2.getX() - bullet2.getWidth(), tank2.getY() + tank2.getHeight());
        bullet2.started = false;
        ((Bullet) bullet2).elapsedTime = 0;
        bullet2.brzinaX = 0;
        bullet2.brzinaY = 0;
        turn++;
    }

    private boolean bulletHitTank(OnScreenObject bullet, OnScreenObject tank) {
        float bulletMiddleX = bullet.getX() + bullet.getWidth() / 2;
        float bulletMiddleY = bullet.getY() + bullet.getHeight() / 2;
        if (inRange(bulletMiddleX, tank.getX(), tank.getX() + tank.getWidth()) &&
                inRange(bulletMiddleY, tank.getY(), tank.getY() + tank.getHeight())) {
            return true;
        } else {
            return false;
        }
    }

    private void increaseScore(OnScreenObject bullet, int player) {
        int type = ((Bullet) bullet).getCurrentType();
        if (player == 1) {
            switch (type) {
                case Bullet.TYPE_0:
                    player1Score += 1;
                    break;
                case Bullet.TYPE_1:
                    player1Score += 3;
                    break;
                case Bullet.TYPE_2:
                    player1Score += 5;
                    break;
            }
        } else {
            switch (type) {
                case Bullet.TYPE_0:
                    player2Score += 1;
                    break;
                case Bullet.TYPE_1:
                    player2Score += 3;
                    break;
                case Bullet.TYPE_2:
                    player2Score += 5;
                    break;
            }
        }
    }

    private void endGame() {
        gameFinished = true;
        if (player1Score > player2Score) {
            ((GameStage) mainStage).getPlayer1Wins().setVisible(true);
        } else if (player1Score == player2Score) {
            ((GameStage) mainStage).getNoWinner().setVisible(true);
        } else {
            ((GameStage) mainStage).getPlayer2Wins().setVisible(true);
        }
        restart.setVisible(true);
    }


    private void restartOnClick(float x, float y) {
        if (inRange(x, restart.getX(), restart.getX() + restart.getWidth()) &&
                inRange(Gdx.graphics.getHeight() - y, restart.getY(), restart.getY() + restart.getHeight())) {
            restartGame();
        }
    }

    private void restartGame() {
        ((GameStage) mainStage).getPlayer1Wins().setVisible(false);
        ((GameStage) mainStage).getNoWinner().setVisible(false);
        ((GameStage) mainStage).getPlayer2Wins().setVisible(false);
        restart.setVisible(false);
        player1Score = 0;
        player1ScoreLabel.setText("0");
        player2Score = 0;
        player2ScoreLabel.setText("0");
        turn = 0;
        gameFinished = false;
        ((Bullet) bullet1).setCurrentType(Bullet.TYPE_0);
        ((Bullet) bullet1).setBulletType0Count(Bullet.TYPE_0_INITIAL_COUNT);
        ((Bullet) bullet1).setBulletType1Count(Bullet.TYPE_1_INITIAL_COUNT);
        ((Bullet) bullet1).setBulletType2Count(Bullet.TYPE_2_INITIAL_COUNT);

        ((Bullet) bullet2).setCurrentType(Bullet.TYPE_0);
        ((Bullet) bullet2).setBulletType0Count(Bullet.TYPE_0_INITIAL_COUNT);
        ((Bullet) bullet2).setBulletType1Count(Bullet.TYPE_1_INITIAL_COUNT);
        ((Bullet) bullet2).setBulletType2Count(Bullet.TYPE_2_INITIAL_COUNT);
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
        if (!gameFinished) {
            if (turn % 2 == 0 && !bullet1.isVisible() && !bullet2.isVisible()) {
                //prvi igrac igra
                if (((Bullet) bullet1).getCurrentTypeAmmo() != 0) {
                    bullet1.setVisible(true);
                    bullet1.started = true;
                    bullet1.brzinaX = velocityX * scale;
                    bullet1.brzinaY = -velocityY * scale;
                    updateBulletsCountState((Bullet) bullet1, ((Bullet) bullet1).getCurrentType());
                } else {
                    ((GameStage)mainStage).getNoAmmo().setVisible(true);
                    new Timer().scheduleTask(new Timer.Task() {
                        @Override
                        public void run() {
                            ((GameStage)mainStage).getNoAmmo().setVisible(false);
                        }
                    }, 1);
                }
            } else if (!bullet1.isVisible() && !bullet2.isVisible()) {
                //drugi igrac igra
                if (((Bullet) bullet2).getCurrentTypeAmmo() != 0) {
                    bullet2.setVisible(true);
                    bullet2.started = true;
                    bullet2.brzinaX = velocityX * scale;
                    bullet2.brzinaY = -velocityY * scale;
                    updateBulletsCountState((Bullet) bullet2, ((Bullet) bullet2).getCurrentType());
                } else {
                    ((GameStage)mainStage).getNoAmmo().setVisible(true);
                    new Timer().scheduleTask(new Timer.Task() {
                        @Override
                        public void run() {
                            ((GameStage)mainStage).getNoAmmo().setVisible(false);
                        }
                    }, 1);
                }
            }
        }

        return false;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        if (!gameFinished) {
            leftArrowOnClick(x, y);
            rightArrowOnClick(x, y);
            weaponOnClick(weaponPicker1, x, y, Bullet.TYPE_0);
            weaponOnClick(weaponPicker2, x, y, Bullet.TYPE_1);
            weaponOnClick(weaponPicker3, x, y, Bullet.TYPE_2);
        } else if (restart.isVisible()) {
            restartOnClick(x, y);
        }
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        if (!gameFinished) {
            leftArrowClicked = false;
            rightArrowClicked = false;
        }
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
        if (!gameFinished) {
            if (initialDistance < distance) {
                camera.zoom *= (1 - ZOOM_SPEED);
            } else {
                camera.zoom *= (1 + ZOOM_SPEED);
            }
            System.out.println("ZOOOOOOM!!!!!!");
        }
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

    private void updateBulletsCountState(Bullet bullet, int type){
        switch (type){
            case 0 :
                bullet.setBulletType0Count(bullet.getBulletType0Count() - 1);
                break;
            case 1 :
                bullet.setBulletType1Count(bullet.getBulletType1Count() - 1);
                break;
            case 2 :
                bullet.setBulletType2Count(bullet.getBulletType2Count() - 1);
                break;
        }
    }

}
