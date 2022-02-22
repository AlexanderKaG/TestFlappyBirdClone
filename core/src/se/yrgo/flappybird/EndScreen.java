package se.yrgo.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class EndScreen implements Screen {

    final FlappyBirdGame game;
    OrthographicCamera camera;

    public EndScreen(final FlappyBirdGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 800);

        addScoreToScoreboard();
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

        int scoreboardLayout = 650;
        int highScore = 0;
        game.batch.begin();
        game.font.draw(game.batch, "Your Score: " + GameScreen.points, 340, 700);
        game.font.draw(game.batch, "Previous scores:", 340, scoreboardLayout);
        for (Integer score : game.scoreboard) {
            game.font.draw(game.batch, Integer.toString(score), 340, scoreboardLayout - 20);
            scoreboardLayout -= 20;
            if (score > highScore) {
                highScore = score;
            }
        }
        game.font.draw(game.batch, "High-score: " + highScore, 340, 200);
        game.font.draw(game.batch, "Tap anywhere to restart", 340, 100);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            GameScreen.points = 0;
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
// method required to implement Screen
    }

    @Override
    public void pause() {
// method required to implement Screen
    }

    @Override
    public void resume() {
// method required to implement Screen
    }

    @Override
    public void hide() {
// method required to implement Screen
    }

    @Override
    public void dispose() {
// method required to implement Screen
    }
}
