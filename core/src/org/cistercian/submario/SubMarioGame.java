package org.cistercian.submario;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SubMarioGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture crateImg, snowImg, redBrickImg, brownBrickImg, coralImg;
	Texture rSeahorseImg, lSeahorseImg;
	Player seahorse;
	Map gameMap = new Map();

	@Override
	public void create () {
		batch = new SpriteBatch();

		// load images
		crateImg = new Texture("crate.png");
		coralImg = new Texture("coral.png");
		snowImg = new Texture("snow.png");
		redBrickImg = new Texture("red_brick.png");
		brownBrickImg = new Texture("brown_brick.png");
		rSeahorseImg = new Texture("rSeahorse.png");
		lSeahorseImg = new Texture("lSeahorse.png");

		// load map
		//gameMap.load("map.csv");

		// create player
		seahorse = new Player(rSeahorseImg, lSeahorseImg);
		Gdx.input.setInputProcessor(seahorse.getInputAdapter());

	}

	@Override
	public void render () {
		seahorse.move();
		Gdx.gl.glClearColor(0.5294f, 0.8078f, 0.98f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for(int i = 0; i < 30; i ++){
			batch.draw(coralImg, 25*i, 0);
		}
		batch.draw(coralImg, 500, 0);
		seahorse.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		crateImg.dispose();
		snowImg.dispose();
		rSeahorseImg.dispose();
		lSeahorseImg.dispose();
	}
}
