package se.yrgo.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class GameScreen implements Screen {
    final FlappyBirdGame game;

    Texture playerImage;
    Texture bottomObstacleImage;
    Texture topObstacleImage;

    OrthographicCamera camera;

    Rectangle player;
    Array<Rectangle> bottomObstacles;
    Array<Rectangle> topObstacles;

    long lastObstacleSpawnTime;

    public GameScreen(final FlappyBirdGame game) {
        this.game = game;

        //load the images for player and obstacles
        playerImage = new Texture(Gdx.files.internal("brownsquare.png"));
        bottomObstacleImage = new Texture(Gdx.files.internal("bottomObstacle.png"));
        topObstacleImage = new Texture(Gdx.files.internal("topObstacle.png"));

        //create the camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 800);

        //create a Rectangle to logically represent the player
        player = new Rectangle();
        player.x = 800/2-64/2;
        player.y = 400;
        player.width = 64;
        player.height = 64;

       //creates two arrays to hold bottomObstacles and topObstacles and spawns the first obstacle
        bottomObstacles = new Array<Rectangle>();
        topObstacles = new Array<Rectangle>();
        spawnObstacles();
    }

    private void spawnObstacles() {
        Rectangle bottomObstacle = new Rectangle();
        bottomObstacle.x = 850;
        bottomObstacle.y = 0;
        bottomObstacle.width = 128;
        bottomObstacle.height = MathUtils.random(100, 500);
        bottomObstacles.add(bottomObstacle);

        Rectangle topObstacle = new Rectangle();
        topObstacle.x = 850;
        topObstacle.width = 128;
        topObstacle.height = 600-bottomObstacle.height;
        topObstacle.y = 800-topObstacle.height;
        topObstacles.add(topObstacle);

        lastObstacleSpawnTime = TimeUtils.nanoTime();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        //background color
        ScreenUtils.clear(0,0,0.8f,1);

        //tell the SpriteBatch to render in the coordinate system specified by the camera
        game.batch.setProjectionMatrix(camera.combined);

        //begin a new batch and draw the player and obstacles
        game.batch.begin();
        game.batch.draw(playerImage, player.x, player.y, player.width, player.height);
        for (Rectangle obstacle : bottomObstacles) {
            game.batch.draw(bottomObstacleImage, obstacle.x, obstacle. y, obstacle.width, obstacle.height);
        }
        for (Rectangle obstacle : topObstacles) {
            game.batch.draw(topObstacleImage, obstacle.x, obstacle. y, obstacle.width, obstacle.height);
        }
        game.batch.end();

        //process user input
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            player.y += 85;
        }

        //make sure the player stays within the screen boundaries
        if (player.y > 800-64) {
            player.y = 800-64;
        }
        if (player.y < 0) {
            player.y = 0;
        }

        //spawn a new obstacle every second
        if (TimeUtils.nanoTime() - lastObstacleSpawnTime > 2000000000) {
            spawnObstacles();
        }

        //move the obstacles, remove any that are outside the left edge of the screen
        Iterator<Rectangle> bottomIter = bottomObstacles.iterator();
        Iterator<Rectangle> topIter = topObstacles.iterator();
        while (bottomIter.hasNext() || topIter.hasNext()) {
            Rectangle bottomObstacle = bottomIter.next();
            Rectangle topObstacle = topIter.next();
            bottomObstacle.x -= 200 * Gdx.graphics.getDeltaTime();
            topObstacle.x -= 200 * Gdx.graphics.getDeltaTime();
            if (bottomObstacle.x + 128 < 0 || topObstacle.x + 128 < 0) {
                bottomIter.remove();
                topIter.remove();
            }
            if (bottomObstacle.overlaps(player) || topObstacle.overlaps(player)) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        }

        //makes the player model fall over time
        player.y -= 200 * Gdx.graphics.getDeltaTime();
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
        playerImage.dispose();
        bottomObstacleImage.dispose();
    }
}
