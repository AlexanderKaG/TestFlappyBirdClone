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

import static se.yrgo.flappybird.Constants.OBSTACLE_STARTING_X_POSITION;
import static se.yrgo.flappybird.Constants.OBSTACLE_WIDTH;
import static se.yrgo.flappybird.Constants.BOTTOM_OBSTACLE_STARTING_Y_POSITION;
import static se.yrgo.flappybird.Constants.PLAYER_STARTING_X_POSITION_CENTERED;
import static se.yrgo.flappybird.Constants.PLAYER_STARTING_Y_POSITION_CENTERED;
import static se.yrgo.flappybird.Constants.PLAYER_WIDTH;
import static se.yrgo.flappybird.Constants.PLAYER_HEIGHT;

public class GameScreen implements Screen {
    private final FlappyBirdGame game;

    private final Texture playerImage;
    private final Texture bottomObstacleImage;
    private final Texture topObstacleImage;

    private final OrthographicCamera camera;

    private final Rectangle player;
    private final Array<Rectangle> bottomObstacles;
    private final Array<Rectangle> topObstacles;

    private long lastObstacleSpawnTime;
    private long lastPointAwardedTime;
    static int points = 0;

    public GameScreen(final FlappyBirdGame game) {
        this.game = game;

        //load the images for player and obstacles
        playerImage = new Texture(Gdx.files.internal("brownSquare.png"));
        bottomObstacleImage = new Texture(Gdx.files.internal("bottomObstacle.png"));
        topObstacleImage = new Texture(Gdx.files.internal("topObstacle.png"));

        //create the camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 800);

        //create a Rectangle to logically represent the player
        player = new Rectangle();
        player.x = PLAYER_STARTING_X_POSITION_CENTERED;
        player.y = PLAYER_STARTING_Y_POSITION_CENTERED;
        player.width = PLAYER_WIDTH;
        player.height = PLAYER_HEIGHT;

        //creates two arrays to hold bottomObstacles and topObstacles and spawns the first obstacle
        bottomObstacles = new Array<>();
        topObstacles = new Array<>();
        spawnObstacles();
    }

    private void spawnObstacles() {
        Rectangle bottomObstacle = new Rectangle();
        bottomObstacle.width = OBSTACLE_WIDTH;
        bottomObstacle.height = MathUtils.random(100, 500);
        bottomObstacle.x = OBSTACLE_STARTING_X_POSITION;
        bottomObstacle.y = BOTTOM_OBSTACLE_STARTING_Y_POSITION;
        bottomObstacles.add(bottomObstacle);

        Rectangle topObstacle = new Rectangle();
        topObstacle.width = OBSTACLE_WIDTH;
        topObstacle.height = 600 - bottomObstacle.height;
        topObstacle.x = OBSTACLE_STARTING_X_POSITION;
        topObstacle.y = 800 - topObstacle.height;
        topObstacles.add(topObstacle);

        lastObstacleSpawnTime = TimeUtils.nanoTime();
    }

    private void addPoints() {
        points += 1;
        lastPointAwardedTime = TimeUtils.nanoTime();
    }

    @Override
    public void show() {
        // method required to implement Screen
    }

    @Override
    public void render(float delta) {

        //background color
        ScreenUtils.clear(0, 0, 0.8f, 1);

        //tell the SpriteBatch to render in the coordinate system specified by the camera
        game.getBatch().setProjectionMatrix(camera.combined);

        //begin a new batch and draw the player and obstacles
        game.getBatch().begin();
        game.getBatch().draw(playerImage, player.x, player.y, player.width, player.height);
        for (Rectangle obstacle : bottomObstacles) {
            game.getBatch().draw(bottomObstacleImage, obstacle.x, obstacle.y, obstacle.width, obstacle.height);
        }
        for (Rectangle obstacle : topObstacles) {
            game.getBatch().draw(topObstacleImage, obstacle.x, obstacle.y, obstacle.width, obstacle.height);
        }
        game.getFont().draw(game.getBatch(), Integer.toString(points), 100, 700);
        game.getBatch().end();

        //process user input
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            player.y += 85;
        }

        //make sure the player stays within the screen boundaries
        if (player.y > 800 - 64) {
            player.y = 800 - 64;
        }
        if (player.y < 0) {
            player.y = 0;
        }

        //spawn a new obstacle every second
        if (TimeUtils.nanoTime() - lastObstacleSpawnTime > 2000000000) {
            spawnObstacles();
        }

        if (TimeUtils.nanoTime() - lastPointAwardedTime > 50000000) {
            addPoints();
        }

        //move the obstacles, remove any that are outside the left edge of the screen
        Iterator<Rectangle> bottomIteration = bottomObstacles.iterator();
        Iterator<Rectangle> topIteration = topObstacles.iterator();
        while (bottomIteration.hasNext() || topIteration.hasNext()) {
            Rectangle bottomObstacle = bottomIteration.next();
            Rectangle topObstacle = topIteration.next();
            bottomObstacle.x -= 200 * Gdx.graphics.getDeltaTime();
            topObstacle.x -= 200 * Gdx.graphics.getDeltaTime();
            if (bottomObstacle.x + 128 < 0 || topObstacle.x + 128 < 0) {
                bottomIteration.remove();
                topIteration.remove();
            }
            if (bottomObstacle.overlaps(player) || topObstacle.overlaps(player)) {
                game.setScreen(new EndScreen(game));
                dispose();
            }
        }

        //makes the player model fall over time
        player.y -= 200 * Gdx.graphics.getDeltaTime();
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
        playerImage.dispose();
        bottomObstacleImage.dispose();
        topObstacleImage.dispose();
    }
}