package org.cistercian.submario;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class SubMarioGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture coralImg, lCoralImg, rCoralImg, vrCoralImg, vlCoralImg;
	Texture rSeahorseImg, lSeahorseImg;
	Player seahorse;
	ArrayList<Texture> floor;
	int x;
	Map gameMap = new Map();

	@Override
	public void create () {
		batch = new SpriteBatch();

		// load images
		coralImg = new Texture("coral.png");
		lCoralImg = new Texture("leftCoral.png");
		rCoralImg = new Texture("rightCoral.png");
		vlCoralImg = new Texture("verticalLeftCoral.png");
		vrCoralImg = new Texture("verticalRightCoral.png");
		lSeahorseImg = new Texture("lSeahorse.png");
		rSeahorseImg = new Texture("rSeahorse.png");

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
		batch.draw(rCoralImg, 31 * 25, 0);		//right corner coral piece
		for(int j = 0; j < 10; j ++){
			for(int i = 1; i <= 30; i ++) {
				batch.draw(coralImg, 25 * i, 150 * j);
			}
			for(int k = 1; k <= 6; k ++){
				batch.draw(vrCoralImg, 31 * 25, j * 150 + 25 * k);
				batch.draw(vlCoralImg, 0, j * 150 + 25 * k);
			}
		}
		seahorse.draw(batch);
		batch.end();

		/*
		floor = new ArrayList<Texture>();
		for(int j = 0; j < 10; j++) {
			x = (int) (Math.random() * 31);
			for (int i = 1; i <= 30; i++) {
				batch.draw(coralImg, 25 * i, 150 * j);
				floor.add(coralImg);
			}
		}
		*/
	}
	
	@Override
	public void dispose (){
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
