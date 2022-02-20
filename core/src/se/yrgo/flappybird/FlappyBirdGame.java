package se.yrgo.flappybird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class FlappyBirdGame extends Game {
	SpriteBatch batch;
	BitmapFont font;

	Array<Integer> scoreboard;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		scoreboard = new Array<>();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}