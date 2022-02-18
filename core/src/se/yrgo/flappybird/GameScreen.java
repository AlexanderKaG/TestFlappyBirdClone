package se.yrgo.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen implements Screen {
    final FlappyBirdGame game;

    Texture player;
    Texture obstacle;

    public GameScreen(final FlappyBirdGame game) {
        this.game = game;

        //load the images for player and obstacles
        player = new Texture(Gdx.files.internal("brownsquare.png"));
        obstacle = new Texture(Gdx.files.internal("mariopipe.png"));

        
    }
}
