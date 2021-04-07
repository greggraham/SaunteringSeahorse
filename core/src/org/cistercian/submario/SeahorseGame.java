package org.cistercian.submario;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

public class SeahorseGame extends ApplicationAdapter {
	SpriteBatch batch;
	private OrthographicCamera camera;
	Texture coralImg, lCoralImg, rCoralImg, vrCoralImg, vlCoralImg;
	Texture rSeahorseImg, lSeahorseImg;
	Texture clickImg;
	Player seahorse;
	ArrayList<Sprite> sprites;
	int m = 30;		//number of "levels" of coral
	BitmapFont score, start, end, reset;
	String level;
	int number;
	private boolean gameOver = false;
	//Map gameMap = new Map();

	@Override
	public void create () {
		batch = new SpriteBatch();

		score = new BitmapFont();
		score.setColor(Color.WHITE);
		score.getData().setScale(2);
		start = new BitmapFont();
		start.setColor(Color.YELLOW);
		end = new BitmapFont();
		end.setColor(Color.RED);
		end.getData().setScale(3);
		reset = new BitmapFont();
		reset.setColor(Color.PURPLE);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);

		// load images
		coralImg = new Texture("coral.png");
		lCoralImg = new Texture("leftCoral.png");
		rCoralImg = new Texture("rightCoral.png");
		vlCoralImg = new Texture("verticalLeftCoral.png");
		vrCoralImg = new Texture("verticalRightCoral.png");
		lSeahorseImg = new Texture("lSeahorse.png");
		rSeahorseImg = new Texture("rSeahorse.png");
		clickImg = new Texture("click.png");

		// create player
		seahorse = new Player(rSeahorseImg, lSeahorseImg);
		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean keyDown(int keycode) {
				switch (keycode) {
					case Input.Keys.LEFT:
						if (!gameOver) {
							seahorse.moveLeft();
						}
						break;
					case Input.Keys.RIGHT:
						if (!gameOver) {
							seahorse.moveRight();
						}
						break;
					case Input.Keys.SPACE:
						if (gameOver) {
							seahorse.restart();
							sprites = createMap();
							camera.setToOrtho(false, 800, 600);
							gameOver = false;
						}
						break;
				}
				return true;
			}
		});
		sprites = createMap();
	}
	private ArrayList<Sprite> createMap() {

		ArrayList<Sprite> sprites = new ArrayList<Sprite>();

		//create horizontal coral sprites
		for(float y = 12.5f; y <= 12.5 + m * 150; y += 150){
			float gap = (int)((Math.random() * 27) + 1) * 25 + 12.5f;
			for(float x = 37.5f; x <= 12.5 + 25 * 30; x += 25){
				if (y < 25 || (x != gap && x != gap + 25 && x != gap + 50 && x != gap + 75)){
					sprites.add(new Sprite(coralImg,x,y));
				}
			}
		}

		//create vertical coral sprites
		for(float y = 37.5f; y <= 37.5 + m * 150; y += 25){
			sprites.add(new Sprite(vlCoralImg,12.5f,y));
			sprites.add(new Sprite(vrCoralImg,12.5f + 25 * 31,y));
		}

		return sprites;
	}

	@Override
	public void render () {
		seahorse.move();
		if(seahorse.checkCollision(sprites)){
			gameOver = true;
			seahorse.stopMotion();

		}

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		if(seahorse.getY() > 300 && seahorse.getY() >= camera.position.y){
			camera.position.set(400, seahorse.getY(), 0);
		}
		else if(seahorse.getY() < camera.position.y - 200 && seahorse.getY() > 100){
			camera.position.set(400, seahorse.getY() + 200, 0);
		}
		Gdx.gl.glClearColor(0.5294f, 0.8078f, 0.98f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(lCoralImg, 0, 0);			//left corner coral piece
		batch.draw(rCoralImg, 31 * 25, 0);	//right corner coral piece
		for(Sprite s: sprites){
			s.draw(batch);
		}
		seahorse.draw(batch);

		if(seahorse.getDeltaX() == 0 && !gameOver){
			start.draw(batch, "use left and right\narrow keys to move", 170, 104);
			batch.draw(clickImg, 110, 73);
			//batch.draw(titleImg, 150, 375);
		}

		if(gameOver){
			end.draw(batch, "GAME OVER", 266, camera.position.y + 240);
			if(seahorse.getY() < 300)
				reset.draw(batch, "click the space bar to restart", 307, 248);
			else
				reset.draw(batch, "click the space bar to restart", 307, camera.position.y + 150);
		}

		number = ((int)(seahorse.getY()/150));
		level = Integer.toString(number);
		if(number < 10)
			score.draw(batch, level, 392, camera.position.y + 280);	//need to find center
		if(number >= 10)
			score.draw(batch, level, 384, camera.position.y + 280);	//ditto
		batch.end();
	}
	
	@Override
	public void dispose(){
		batch.dispose();
		coralImg.dispose();
		vlCoralImg.dispose();
		vrCoralImg.dispose();
		lCoralImg.dispose();
		rCoralImg.dispose();
		rSeahorseImg.dispose();
		lSeahorseImg.dispose();
	}
}
