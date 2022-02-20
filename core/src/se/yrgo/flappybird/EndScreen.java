package se.yrgo.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class EndScreen implements Screen {

    final FlappyBirdGame game;
    OrthographicCamera camera;

    Array<Integer> scoreboard;

    public EndScreen(final FlappyBirdGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 800);

        scoreboard = new Array<>();
    }

    private void addScoreToScoreboard() {
        scoreboard.add(GameScreen.points);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0.8f,1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        addScoreToScoreboard();
        int scoreboardLayout = 550;
        game.batch.begin();
        game.font.draw(game.batch, "Your Score: " + GameScreen.points, 300, 600);
        for (Integer score : scoreboard) {
            game.font.draw(game.batch, "Previous scores: " + score, 300, scoreboardLayout - 10);
        }
        game.font.draw(game.batch, "Tap anywhere to restart", 300, 500);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            GameScreen.points = 0;
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
