package com.busrayalcin.fightforlife;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class FightForLife extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture bird;
	Texture bee;
	Texture bee2;
	Texture bee3;
	float birdX = 0;
	float birdY = 0;
	int gameState = 0;
	float velocity = 0 ;
	float gravity = 0.3f;
	float enemyVelocity = 2;
	Random random;

	int numberOfEnemies = 4;
	float [] enemyX = new float[numberOfEnemies];
	float [] enemyOffSet = new float[numberOfEnemies];
	float [] enemyOffSet2 = new float[numberOfEnemies];
	float [] enemyOffSet3 = new float[numberOfEnemies];

	float distance;

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("background.png");
		bird = new Texture("bird.png");
		bee = new Texture("bee.png");
		bee2 = new Texture("bee.png");
		bee3 = new Texture("bee.png");

		distance = Gdx.graphics.getWidth() / 2;
		random = new Random();


		birdX = Gdx.graphics.getWidth() / 2-bird.getHeight() / 2;
		birdY = Gdx.graphics.getHeight() / 3;

		for (int i = 0;i<numberOfEnemies;i++){

			enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
			enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
			enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() -200);

			enemyX[i] = Gdx.graphics.getWidth() - bee.getWidth() / 2 + i * distance;
		}
	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		if (gameState == 1){

			if (Gdx.input.justTouched()){
				velocity = -8;
			}
			for (int i = 0; i<numberOfEnemies; i++){

				if (enemyX[i] < Gdx.graphics.getWidth()/15){
					enemyX[i] = enemyX[i] + numberOfEnemies * distance;

					enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() -200);

				}else {
					enemyX[i] = enemyX[i] - enemyVelocity ;
				}

				batch.draw(bee,enemyX[i],Gdx.graphics.getHeight() / 2 + enemyOffSet[i],Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
				batch.draw(bee2,enemyX[i],Gdx.graphics.getHeight() / 2 + enemyOffSet2[i],Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
				batch.draw(bee3,enemyX[i],Gdx.graphics.getHeight() / 2 + enemyOffSet3[i],Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
			}

			if (birdY > 0 || velocity <0){
				velocity = velocity + gravity;
				birdY = birdY - velocity;

			}else {

			}
		}else {
			if (Gdx.input.justTouched()){
				System.out.println("touched");
				gameState = 1;
			}
		}

		batch.draw(bird,birdX,birdY,Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);

		batch.end();
	}

	@Override
	public void dispose () {

	}
}