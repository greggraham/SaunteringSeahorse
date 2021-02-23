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

    public Player(Texture playerImage) {
        super(playerImage);
        stopMotion();
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, rect.x, rect.y);
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
        vertSpeed -= 550 * Gdx.graphics.getDeltaTime();
    }

    private void stopMotion() {
        deltaX = 0;
    }
}
