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

    //Array<Integer> scoreboard;

    public EndScreen(final FlappyBirdGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 800);

        addScoreToScoreboard();
        //scoreboard = new Array<>();
    }

    private void addScoreToScoreboard() {
        game.scoreboard.add(GameScreen.points);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0.8f,1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        int scoreboardLayout = 550;
        int highScore = 0;
        game.batch.begin();
        game.font.draw(game.batch, "Your Score: " + GameScreen.points, 300, 600);
        game.font.draw(game.batch, "Previous scores:", 300, scoreboardLayout);
        for (Integer score : game.scoreboard) {
            game.font.draw(game.batch, Integer.toString(score), 300, scoreboardLayout - 20);
            scoreboardLayout -= 20;
            if (score > highScore) {
                highScore = score;
            }
        }
        game.font.draw(game.batch, "High-score: " + highScore, 300, 200);
        game.font.draw(game.batch, "Tap anywhere to restart", 300, 100);
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
