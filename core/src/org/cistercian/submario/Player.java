package org.cistercian.submario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Player extends Sprite {
    private final float speed = 100; // pixels per second
    private float deltaX, deltaY;

    public Player(Texture playerImage) {
        super(playerImage);
        stopMotion();
    }

    public InputProcessor getInputAdapter() {
        return (new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.LEFT:
                        deltaX = -speed;
                        deltaY = speed;
                        break;
                    case Input.Keys.RIGHT:
                        deltaX = speed;
                        deltaY = speed;
                        break;
                }
                return true;
            }

            @Override
            public boolean keyUp(int keycode) {
                switch (keycode) {
                    case Input.Keys.LEFT:
                        deltaX = -speed;
                        deltaY = -speed;
                        break;
                    case Input.Keys.RIGHT:
                        deltaX = speed;
                        deltaY = -speed;
                        break;
                }
                return true;
            }
        });
    }

    public void move() {
        rect.x += deltaX * Gdx.graphics.getDeltaTime();
        rect.y += deltaY * Gdx.graphics.getDeltaTime();
    }

    private void stopMotion() {
        deltaX = 0;
        deltaY = 0;
    }
}
