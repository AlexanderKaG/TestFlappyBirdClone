package se.yrgo.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
    private final FlappyBirdGame game;
    private final OrthographicCamera camera;

    public MainMenuScreen(final FlappyBirdGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 800);
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

        game.getBatch().begin();
        game.getFont().draw(game.getBatch(), "Welcome to my Flappy Bird clone!!!", 300, 600);
        game.getFont().draw(game.getBatch(), "Tap anywhere to begin", 300, 500);
        game.getBatch().end();

        if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
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