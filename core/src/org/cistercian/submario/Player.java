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
    private int direction = 1;

    public Player(Texture rSeahorseImage, Texture lSeahorseImage) {
        super(rSeahorseImage);
        this.rSeahorseImage = rSeahorseImage;
        this.lSeahorseImage = lSeahorseImage;
        rect.x = 40;
        rect.y = 40;
        stopMotion();
    }

    public void draw(SpriteBatch batch){
        if(direction == -1)
            batch.draw(lSeahorseImage, rect.x, rect.y);
        else
            batch.draw(rSeahorseImage, rect.x, rect.y);
    }

    public void move() {
        rect.x += deltaX * Gdx.graphics.getDeltaTime();
        rect.y += vertSpeed * Gdx.graphics.getDeltaTime();
        if(deltaX != 0)
            vertSpeed -= 550 * Gdx.graphics.getDeltaTime();
    }

    public boolean checkCollision(ArrayList<Sprite> sprites){
        for(Sprite s : sprites){
            if((this.getRect()).overlaps(s.getRect())){
                return true;
            }
        }
        return false;
    }

    public void moveLeft() {
        deltaX = -speed;
        vertSpeed = jumpSpeed;
        direction = -1;
    }

    public void moveRight() {
        deltaX = speed;
        vertSpeed = jumpSpeed;
        direction = 1;
    }

    public void stopMotion() {
        deltaX = 0;
        vertSpeed = 0;
    }

    public void restart() {
        rect.x = 40;
        rect.y = 40;
        direction = 1;
    }

    public float getDeltaX(){
        return deltaX;
    }
}