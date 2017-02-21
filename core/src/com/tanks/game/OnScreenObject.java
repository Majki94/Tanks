package com.tanks.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by majkic on 17.2.17..
 */

public class OnScreenObject extends Actor {

    public TextureRegion region;
    public Rectangle granica;
    public float brzinaX;
    public float brzinaY;
    boolean started = false;


    public OnScreenObject() {
        super();
        region = new TextureRegion();
        granica = new Rectangle();
        brzinaX = 0;
        brzinaY = 0;


    }

    public void setTexture(Texture t) {
        int w = t.getWidth();
        int h = t.getHeight();
        setWidth(w);
        setHeight(h);
        region.setRegion(t);
    }

    public Rectangle getGranica() {
        granica.set(getX(), getY(), getWidth(), getHeight());
        return granica;
    }

    public void act(float dt) {
        if (started) {
            moveBy(brzinaX * dt, brzinaY * dt);
        }
    }

    public void draw(Batch b, float parentAlpha) {
        Color c = getColor();
        b.setColor(c.r, c.g, c.b, c.a);
        if (isVisible()) {
            b.draw(region, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }
}
