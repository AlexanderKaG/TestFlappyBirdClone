package se.yrgo.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class EndScreen implements Screen {

    private final FlappyBirdGame game;
    private final OrthographicCamera camera;

    public EndScreen(final FlappyBirdGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 800);

        addScoreToScoreboard();
    }

    private void addScoreToScoreboard() {
        game.getScoreboard().add(GameScreen.points);
    }

    @Override
    public void show() {
// method required to implement Screen
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.8f, 1);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        int scoreboardLayout = 650;
        int highScore = 0;
        game.getBatch().begin();
        game.getFont().draw(game.getBatch(), "Your Score: " + GameScreen.points, 340, 700);
        game.getFont().draw(game.getBatch(), "Previous scores:", 340, scoreboardLayout);
        for (Integer score : game.getScoreboard()) {
            game.getFont().draw(game.getBatch(), Integer.toString(score), 340, scoreboardLayout - 20);
            scoreboardLayout -= 20;
            if (score > highScore) {
                highScore = score;
            }
        }
        game.getFont().draw(game.getBatch(), "High-score: " + highScore, 340, 200);
        game.getFont().draw(game.getBatch(), "Tap anywhere to restart", 340, 100);
        game.getBatch().end();

        if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
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
