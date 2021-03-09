package org.cistercian.submario;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

public class SubMarioGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture coralImg, lCoralImg, rCoralImg, vrCoralImg, vlCoralImg;
	Texture rSeahorseImg, lSeahorseImg;
	Player seahorse;
	ArrayList<Sprite> sprites;
	//Map gameMap = new Map();

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

		// create player
		seahorse = new Player(rSeahorseImg, lSeahorseImg);
		Gdx.input.setInputProcessor(seahorse.getInputAdapter());

		//create coral sprites
		sprites = new ArrayList<Sprite>();
		int gap = (int)((Math.random() * 30) + 1) * 25;
		for(int y = 0; y < 10 * 150; y += 150){
			for(int x = 25; x <= 25 * 30; x += 25){
				if (x != gap && x != gap + 10){
					sprites.add(new Sprite(coralImg,x,y));
				}
			}
		}
	}

	@Override
	public void render () {
		seahorse.move();
		Gdx.gl.glClearColor(0.5294f, 0.8078f, 0.98f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(lCoralImg, 0, 0);			//left corner coral piece
		batch.draw(rCoralImg, 31 * 25, 0);	//right corner coral piece
		for(Sprite s: sprites){
			s.draw();
		}
		seahorse.draw(batch);
		batch.end();
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
