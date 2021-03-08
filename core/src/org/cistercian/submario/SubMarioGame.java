package org.cistercian.submario;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SubMarioGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture crateImg, snowImg, redBrickImg, brownBrickImg, coralImg, lCoralImg, rCoralImg, vrCoralImg, vlCoralImg;
	Texture rSeahorseImg, lSeahorseImg;
	Player seahorse;
	Map gameMap = new Map();

	@Override
	public void create () {
		batch = new SpriteBatch();

		// load images
		crateImg = new Texture("crate.png");
		coralImg = new Texture("coral.png");
		lCoralImg = new Texture("leftCoral.png");
		rCoralImg = new Texture("rightCoral.png");
		vrCoralImg = new Texture("verticalRightCoral.png");
		vlCoralImg = new Texture("verticalLeftCoral.png");
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
		batch.draw(lCoralImg, 0, 0);			//left corner coral piece
		batch.draw(rCoralImg, 31*25, 0);		//right corner coral piece
		for(int i = 1; i < 31; i ++){
			batch.draw(coralImg, 25*i, 0);
			batch.draw(coralImg, 25*i, 150);
			batch.draw(coralImg, 25*i, 300);
			batch.draw(coralImg, 25*i, 450);
			batch.draw(coralImg, 25*i, 600);
			batch.draw(vrCoralImg,31*25, 25*i);
			batch.draw(vlCoralImg,0, 25*i);
		}
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
