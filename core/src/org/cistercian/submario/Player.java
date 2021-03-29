package org.cistercian.submario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Player extends Sprite {
    private final float speed = 70; // pixels per second
    private float deltaX;
    private final float jumpSpeed = 240;
    private float vertSpeed = 0;
    private Texture rSeahorseImage, lSeahorseImage;
    private boolean gameOver = false;

    public Player(Texture rSeahorseImage, Texture lSeahorseImage) {
        super(rSeahorseImage);
        this.rSeahorseImage = rSeahorseImage;
        this.lSeahorseImage = lSeahorseImage;
        rect.x = 40;
        rect.y = 40;
        stopMotion();
    }

    public void draw(SpriteBatch batch){
        if(deltaX == 0 && !gameOver){
            batch.draw(rSeahorseImage, rect.x, rect.y);
            vertSpeed -= 30 * Gdx.graphics.getDeltaTime();
            rect.y += vertSpeed * Gdx.graphics.getDeltaTime();
            if(rect.y < 40)
                vertSpeed = 15;
        }
        else if(deltaX < 0)
            batch.draw(lSeahorseImage, rect.x, rect.y);
        else
            batch.draw(rSeahorseImage, rect.x, rect.y);
    }

    public InputProcessor getInputAdapter() {
        return (new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.LEFT:
                        if(!gameOver) {
                            deltaX = -speed;
                            vertSpeed = jumpSpeed;
                            break;
                        }
                    case Input.Keys.RIGHT:
                        if(!gameOver) {
                            deltaX = speed;
                            vertSpeed = jumpSpeed;
                            break;
                        }
                }
                return true;
            }
        });
    }

    public void move(ArrayList<Sprite> sprites) {
        rect.x += deltaX * Gdx.graphics.getDeltaTime();
        rect.y += vertSpeed * Gdx.graphics.getDeltaTime();
        if(deltaX != 0)
            vertSpeed -= 550 * Gdx.graphics.getDeltaTime();

        for(Sprite s : sprites){
            if((this.getRect()).overlaps(s.getRect())){
                gameOver = true;
                deltaX = 0;
                vertSpeed = 0;
            }
        }
    }

    private void stopMotion() {
        deltaX = 0;
    }

    public float getDeltaX(){
        return deltaX;
    }

    public boolean getGameOver(){
        return gameOver;
    }
}