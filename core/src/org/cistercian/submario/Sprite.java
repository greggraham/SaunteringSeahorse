package org.cistercian.submario;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Sprite {
    protected Rectangle rect;
    protected Texture texture;

    public Sprite(Texture spriteImage) {
        rect = new Rectangle();
        texture = spriteImage;
        rect.width = texture.getWidth();
        rect.height = texture.getHeight();
        rect.x = 40;
        rect.y = 388;
    }

    public Sprite(Texture spriteImage, float centerX, float centerY) {
        this(spriteImage);
        setCenter(centerX, centerY);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, rect.x, rect.y);
    }

    public void setCenter(float centerX, float centerY) {
        rect.x = centerX - rect.width / 2;
        rect.y = centerY - rect.height / 2;
    }

    public float getY(){
        return rect.y;
    }

    public Rectangle getRect(){
        return rect;
    }
}
