package org.cistercian.submario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Player extends Sprite {
    private final float speed = 70; // pixels per second
    private float deltaX;
    private final float jumpSpeed = 240;
    private float vertSpeed = 0;
    private Texture rSeahorseImage, lSeahorseImage;

    public Player(Texture rSeahorseImage, Texture lSeahorseImage) {
        super(rSeahorseImage);
        this.rSeahorseImage = rSeahorseImage;
        this.lSeahorseImage = lSeahorseImage;
        stopMotion();
    }

    public void draw(SpriteBatch batch){
        if(deltaX == 0){
            batch.draw(rSeahorseImage, 40, rect.y + 30);
            vertSpeed -= 30 * Gdx.graphics.getDeltaTime();
            rect.y += vertSpeed * Gdx.graphics.getDeltaTime();
            if(rect.y < 0)
                vertSpeed = 15;
        }
        else if(deltaX < 0)
            batch.draw(lSeahorseImage, 40 + rect.x, 40 + rect.y);
        else
            batch.draw(rSeahorseImage, 40 + rect.x, 40 + rect.y);
    }

    public InputProcessor getInputAdapter() {
        return (new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.LEFT:
                        deltaX = -speed;
                        vertSpeed = jumpSpeed;
                        break;
                    case Input.Keys.RIGHT:
                        deltaX = speed;
                        vertSpeed = jumpSpeed;
                        break;
                }
                return true;
            }

            @Override
            public boolean keyUp(int keycode) {
                switch (keycode) {
                    case Input.Keys.LEFT:
                        deltaX = -speed;
                        break;
                    case Input.Keys.RIGHT:
                        deltaX = speed;
                        break;
                }
                return true;
            }
        });
    }

    public void move() {
        rect.x += deltaX * Gdx.graphics.getDeltaTime();
        rect.y += vertSpeed * Gdx.graphics.getDeltaTime();
        if(deltaX != 0)
            vertSpeed -= 550 * Gdx.graphics.getDeltaTime();
    }

    private void stopMotion() {
        deltaX = 0;
    }

    public float getDeltaX(){
        return deltaX;
    }
}