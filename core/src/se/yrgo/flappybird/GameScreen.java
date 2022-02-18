package se.yrgo.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen implements Screen {
    final FlappyBirdGame game;

    Texture playerImage;
    Texture obstacleImage;

    OrthographicCamera camera;

    Rectangle player;

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

        
    }
}
