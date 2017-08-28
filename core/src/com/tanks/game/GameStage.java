package com.tanks.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

/**
 * Created by majkic on 17.2.17..
 */

public class GameStage extends Stage {

    private static final int WEAPON_PICKER_SIZE = 50;
    private static final int tankWidth = 80;
    private static final int tankHeight = 62;
    public static final int tank1InitialPosX = 40;
    public static final int tank1InitialPosY = Gdx.graphics.getHeight() / 3;
    public static final int tank2InitialPosX = Gdx.graphics.getWidth() - tankWidth - 40;
    public static final int tank2InitialPosY = Gdx.graphics.getHeight() / 3;
    public static final int BULLET_SIZE = 15;

    private OnScreenObject land;
    private OnScreenObject sky;
    private OnScreenObject tank1;
    private OnScreenObject tank2;
    private OnScreenObject bullet1;
    private OnScreenObject bullet2;
    private OnScreenObject leftArrow;
    private OnScreenObject rightArrow;
    private OnScreenObject player1StringImage;
    private OnScreenObject player2StringImage;
    private BitmapFont font;
    private Label player1ScoreLabel;
    private Label player2ScoreLabel;
    private Label roundLabel;
    private OnScreenObject player1Wins;
    private OnScreenObject player2Wins;
    private OnScreenObject noWinner;
    private OnScreenObject weaponPickerBg;
    private OnScreenObject weaponPicker1;
    private OnScreenObject weaponPicker2;
    private OnScreenObject weaponPicker3;
    private OnScreenObject restart;
    private Label type0CountLabel;
    private Label type1CountLabel;
    private Label type2CountLabel;
    private Label noAmmo;

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
        tank1.setWidth(tankWidth);
        tank1.setHeight(tankHeight);
        tank1.setPosition(tank1InitialPosX, tank1InitialPosY);

        tank2 = new OnScreenObject();
        tank2.setTexture(new Texture("tank2.png"));
        tank2.setVisible(true);
        tank2.setWidth(tankWidth);
        tank2.setHeight(tankHeight);
        tank2.setPosition(tank2InitialPosX, tank2InitialPosY);

        bullet1 = new Bullet();
        bullet1.setTexture(new Texture("bullet.png"));
        bullet1.setVisible(false);
        bullet1.setWidth(BULLET_SIZE);
        bullet1.setHeight(BULLET_SIZE);
        bullet1.setPosition(tank1.getX() + tank1.getWidth(), tank1.getY() + tank1.getHeight());

        bullet2 = new Bullet();
        bullet2.setTexture(new Texture("bullet.png"));
        bullet2.setVisible(false);
        bullet2.setWidth(BULLET_SIZE);
        bullet2.setHeight(BULLET_SIZE);
        bullet2.setPosition(tank2.getX() - bullet2.getWidth(), tank2.getY() + tank2.getHeight());

        leftArrow = new OnScreenObject();
        leftArrow.setTexture(new Texture("leftArrow.png"));
        leftArrow.setVisible(true);
        leftArrow.setWidth(60);
        leftArrow.setHeight(60);
        leftArrow.setPosition(30, 30);

        rightArrow = new OnScreenObject();
        rightArrow.setTexture(new Texture("rightArrow.png"));
        rightArrow.setVisible(true);
        rightArrow.setWidth(60);
        rightArrow.setHeight(60);
        rightArrow.setPosition(120, 30);

        player1StringImage = new OnScreenObject();
        player1StringImage.setTexture(new Texture("player1.png"));
        player1StringImage.setVisible(false);
        player1StringImage.setWidth(100);
        player1StringImage.setHeight(30);
        player1StringImage.setPosition((Gdx.graphics.getWidth() - player1StringImage.getWidth()) / 2, Gdx.graphics.getHeight() - 60);

        player2StringImage = new OnScreenObject();
        player2StringImage.setTexture(new Texture("player2.png"));
        player2StringImage.setVisible(false);
        player2StringImage.setWidth(100);
        player2StringImage.setHeight(30);
        player2StringImage.setPosition((Gdx.graphics.getWidth() - player1StringImage.getWidth()) / 2, Gdx.graphics.getHeight() - 60);

        font = new BitmapFont();

        player1ScoreLabel = new Label("0", new Label.LabelStyle(font, Color.BLACK));
        player1ScoreLabel.setAlignment(Align.center);
        player1ScoreLabel.setPosition(Gdx.graphics.getWidth() * 0.05f, Gdx.graphics.getHeight() * 0.93f);
        player1ScoreLabel.setFontScale(1);

        player2ScoreLabel = new Label("0", new Label.LabelStyle(font, Color.BLACK));
        player2ScoreLabel.setAlignment(Align.center);
        player2ScoreLabel.setPosition(Gdx.graphics.getWidth() * 0.95f, Gdx.graphics.getHeight() * 0.93f);
        player2ScoreLabel.setFontScale(1);

        roundLabel = new Label("0", new Label.LabelStyle(font, Color.BLACK));
        roundLabel.setAlignment(Align.center);
        roundLabel.setPosition((Gdx.graphics.getWidth() - roundLabel.getWidth()) / 2, Gdx.graphics.getHeight() - 20);
        roundLabel.setFontScale(1);

        weaponPickerBg = new OnScreenObject();
        weaponPickerBg.setTexture(new Texture("weaponPickerBg.png"));
        weaponPickerBg.setVisible(true);
        weaponPickerBg.setWidth(WEAPON_PICKER_SIZE);
        weaponPickerBg.setHeight(WEAPON_PICKER_SIZE);
        weaponPickerBg.setPosition(getWidth() - 3 * WEAPON_PICKER_SIZE - 10, 10);

        weaponPicker1 = new OnScreenObject();
        weaponPicker1.setTexture(new Texture("bullet.png"));
        weaponPicker1.setVisible(true);
        weaponPicker1.setWidth(WEAPON_PICKER_SIZE);
        weaponPicker1.setHeight(WEAPON_PICKER_SIZE);
        weaponPicker1.setPosition(getWidth() - 3 * WEAPON_PICKER_SIZE - 10, 10);

        weaponPicker2 = new OnScreenObject();
        weaponPicker2.setTexture(new Texture("blueBullet.png"));
        weaponPicker2.setVisible(true);
        weaponPicker2.setWidth(WEAPON_PICKER_SIZE);
        weaponPicker2.setHeight(WEAPON_PICKER_SIZE);
        weaponPicker2.setPosition(getWidth() - 2 * WEAPON_PICKER_SIZE - 10, 10);

        weaponPicker3 = new OnScreenObject();
        weaponPicker3.setTexture(new Texture("redBullet.png"));
        weaponPicker3.setVisible(true);
        weaponPicker3.setWidth(WEAPON_PICKER_SIZE);
        weaponPicker3.setHeight(WEAPON_PICKER_SIZE);
        weaponPicker3.setPosition(getWidth() - WEAPON_PICKER_SIZE - 10, 10);

        player1Wins = new OnScreenObject();
        player1Wins.setTexture(new Texture("player1Wins.png"));
        player1Wins.setVisible(false);
        player1Wins.setWidth(500);
        player1Wins.setHeight(60);
        player1Wins.setPosition((Gdx.graphics.getWidth() - player1Wins.getWidth())/2, (Gdx.graphics.getHeight() - player1Wins.getHeight())/2);

        player2Wins = new OnScreenObject();
        player2Wins.setTexture(new Texture("player2Wins.png"));
        player2Wins.setVisible(false);
        player2Wins.setWidth(500);
        player2Wins.setHeight(60);
        player2Wins.setPosition((Gdx.graphics.getWidth() - player2Wins.getWidth())/2, (Gdx.graphics.getHeight() - player2Wins.getHeight())/2);

        noWinner = new OnScreenObject();
        noWinner.setTexture(new Texture("noWinner.png"));
        noWinner.setVisible(false);
        noWinner.setWidth(500);
        noWinner.setHeight(60);
        noWinner.setPosition((Gdx.graphics.getWidth() - noWinner.getWidth())/2, (Gdx.graphics.getHeight() - noWinner.getHeight())/2);

        restart = new OnScreenObject();
        restart.setTexture(new Texture("restart.png"));
        restart.setVisible(false);
        restart.setWidth(80);
        restart.setHeight(80);
        restart.setPosition((Gdx.graphics.getWidth() - restart.getWidth())/2, (Gdx.graphics.getHeight() - restart.getHeight())/2 - restart.getHeight());

        type0CountLabel = new Label("0", new Label.LabelStyle(font, Color.BLACK));
        type0CountLabel.setAlignment(Align.center);
        type0CountLabel.setVisible(true);
        type0CountLabel.setPosition(getWidth() - 3 * WEAPON_PICKER_SIZE - 10, 10);
        type0CountLabel.setFontScale(1);

        type1CountLabel = new Label("0", new Label.LabelStyle(font, Color.BLACK));
        type1CountLabel.setAlignment(Align.center);
        type1CountLabel.setVisible(true);
        type1CountLabel.setPosition(getWidth() - 2 * WEAPON_PICKER_SIZE - 10, 10);
        type1CountLabel.setFontScale(1);

        type2CountLabel = new Label("0", new Label.LabelStyle(font, Color.BLACK));
        type2CountLabel.setAlignment(Align.center);
        type2CountLabel.setVisible(true);
        type2CountLabel.setPosition(getWidth() - WEAPON_PICKER_SIZE - 10, 10);
        type2CountLabel.setFontScale(1);

        noAmmo = new Label("No Ammo", new Label.LabelStyle(font, Color.RED));
        noAmmo.setAlignment(Align.center);
        noAmmo.setVisible(false);
        noAmmo.setPosition(getWidth()/2 - noAmmo.getWidth()/2, getHeight()/2 - noAmmo.getHeight()/2);
        noAmmo.setFontScale(1);

        addActor(land);
        addActor(sky);
        addActor(tank1);
        addActor(tank2);
        addActor(bullet1);
        addActor(bullet2);
        addActor(leftArrow);
        addActor(rightArrow);
        addActor(player1StringImage);
        addActor(player2StringImage);
        addActor(player1ScoreLabel);
        addActor(player2ScoreLabel);
        addActor(roundLabel);
        addActor(weaponPicker1);
        addActor(weaponPicker2);
        addActor(weaponPicker3);
        addActor(weaponPickerBg);
        addActor(player1Wins);
        addActor(player2Wins);
        addActor(noWinner);
        addActor(restart);
        addActor(type0CountLabel);
        addActor(type1CountLabel);
        addActor(type2CountLabel);
        addActor(noAmmo);
    }

    public void updateBullet1() {
        bullet1.setPosition(tank1.getX() + tank1.getWidth(), tank1.getY() + tank1.getHeight());
    }

    public void updateBullet2() {
        bullet2.setPosition(tank2.getX() - bullet2.getWidth(), tank2.getY() + tank2.getHeight());
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

    public OnScreenObject getPlayer1StringImage() {
        return player1StringImage;
    }

    public void setPlayer1StringImage(OnScreenObject player1StringImage) {
        this.player1StringImage = player1StringImage;
    }

    public OnScreenObject getPlayer2StringImage() {
        return player2StringImage;
    }

    public void setPlayer2StringImage(OnScreenObject player2StringImage) {
        this.player2StringImage = player2StringImage;
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }

    public Label getPlayer1ScoreLabel() {
        return player1ScoreLabel;
    }

    public void setPlayer1ScoreLabel(Label player1ScoreLabel) {
        this.player1ScoreLabel = player1ScoreLabel;
    }

    public Label getPlayer2ScoreLabel() {
        return player2ScoreLabel;
    }

    public void setPlayer2ScoreLabel(Label player2ScoreLabel) {
        this.player2ScoreLabel = player2ScoreLabel;
    }

    public OnScreenObject getWeaponPicker1() {
        return weaponPicker1;
    }

    public void setWeaponPicker1(OnScreenObject weaponPicker1) {
        this.weaponPicker1 = weaponPicker1;
    }

    public OnScreenObject getWeaponPicker2() {
        return weaponPicker2;
    }

    public void setWeaponPicker2(OnScreenObject weaponPicker2) {
        this.weaponPicker2 = weaponPicker2;
    }

    public OnScreenObject getWeaponPicker3() {
        return weaponPicker3;
    }

    public void setWeaponPicker3(OnScreenObject weaponPicker3) {
        this.weaponPicker3 = weaponPicker3;
    }

    public OnScreenObject getWeaponPickerBg() {
        return weaponPickerBg;
    }

    public void setWeaponPickerBg(OnScreenObject weaponPickerBg) {
        this.weaponPickerBg = weaponPickerBg;
    }

    public Label getRoundLabel() {
        return roundLabel;
    }

    public void setRoundLabel(Label roundLabel) {
        this.roundLabel = roundLabel;
    }

    public OnScreenObject getPlayer1Wins() {
        return player1Wins;
    }

    public void setPlayer1Wins(OnScreenObject player1Wins) {
        this.player1Wins = player1Wins;
    }

    public OnScreenObject getPlayer2Wins() {
        return player2Wins;
    }

    public void setPlayer2Wins(OnScreenObject player2Wins) {
        this.player2Wins = player2Wins;
    }

    public OnScreenObject getNoWinner() {
        return noWinner;
    }

    public void setNoWinner(OnScreenObject noWinner) {
        this.noWinner = noWinner;
    }

    public OnScreenObject getRestart() {
        return restart;
    }

    public void setRestart(OnScreenObject restart) {
        this.restart = restart;
    }

    public Label getType0CountLabel() {
        return type0CountLabel;
    }

    public void setType0CountLabel(Label type0CountLabel) {
        this.type0CountLabel = type0CountLabel;
    }

    public Label getType1CountLabel() {
        return type1CountLabel;
    }

    public void setType1CountLabel(Label type1CountLabel) {
        this.type1CountLabel = type1CountLabel;
    }

    public Label getType2CountLabel() {
        return type2CountLabel;
    }

    public void setType2CountLabel(Label type2CountLabel) {
        this.type2CountLabel = type2CountLabel;
    }

    public Label getNoAmmo() {
        return noAmmo;
    }

    public void setNoAmmo(Label noAmmo) {
        this.noAmmo = noAmmo;
    }
}
