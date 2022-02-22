package se.yrgo.flappybird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class FlappyBirdGame extends Game {
    private SpriteBatch batch;
    private BitmapFont font;

    private Array<Integer> scoreboard;

    private static final float PLAYER_X_POSITION_CENTERED = (float) Constants.getWidth() / 2 - (float) GameScreen.getPlayerWidth() / 2;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        scoreboard = new Array<>();
        this.setScreen(new MainMenuScreen(this));
    }

    public static float getPLAYER_X_POSITION_CENTERED() {
        return PLAYER_X_POSITION_CENTERED;
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