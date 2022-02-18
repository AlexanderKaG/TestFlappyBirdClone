package se.yrgo.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {
    final FlappyBirdGame game;

    Texture playerImage;
    Texture obstacleImage;

    OrthographicCamera camera;

    Rectangle player;
    Array<Rectangle> obstacles;

    long lastObstacleSpawnTime;

    public GameScreen(final FlappyBirdGame game) {
        this.game = game;

        //load the images for player and obstacles
        playerImage = new Texture(Gdx.files.internal("brownsquare.png"));
        obstacleImage = new Texture(Gdx.files.internal("mariopipe.png"));

        //create the camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 800);

        //create a Rectangle to logically represent the player
        player = new Rectangle;
        player.x = 800/2-64/2;
        player.y = 400;
        player.width = 64;
        player.height = 64;

       //creates and array to hold obstacles and spawns the first obstacle
        obstacles = new Array<Rectangle>();
        spawnObstacles();
    }

    private void spawnObstacles() {
        Rectangle obstacle = new Rectangle();
        obstacle.x = 800-128;
        obstacle.y = 0;
        obstacle.width = 128;
        obstacle.height = 300;
        obstacles.add(obstacle);
        lastObstacleSpawnTime = TimeUtils.nanoTime();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        //background color
        ScreenUtils.clear(0,0,0.8f,1);
        
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
