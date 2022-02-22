package se.yrgo.flappybird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class FlappyBirdGame extends Game {
    private SpriteBatch batch;
    private BitmapFont font;

    private Array<Integer> scoreboard;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        scoreboard = new Array<>();
        this.setScreen(new MainMenuScreen(this));
    }

    public SpriteBatch getBatch() {
        return this.batch;
    }

    public BitmapFont getFont() {
        return this.font;
    }

    public Array<Integer> getScoreboard() {
        return scoreboard;
    }

    //@Override
    //public void render() {
    //    super.render();
    //}

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}