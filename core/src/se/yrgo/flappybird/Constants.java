package se.yrgo.flappybird;

public class Constants {

    // Width and height of application window
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 800;

    // Background color
    public static final float[] BACKGROUND_COLOR_BLUE = {0, 0, 0.8f, 1};

    // Width, height, starting_x_position and starting_y_position of player
    public static final int PLAYER_WIDTH = 64;
    public static final int PLAYER_HEIGHT = 64;
    public static final float PLAYER_STARTING_X_POSITION_CENTERED = (float) WINDOW_WIDTH / 2 - (float)PLAYER_WIDTH / 2;
    public static final float PLAYER_STARTING_Y_POSITION_CENTERED = (float) WINDOW_HEIGHT / 2 - (float)PLAYER_HEIGHT / 2;

    // Width and starting_x_position of any obstacles
    public static final int OBSTACLE_WIDTH = 128;
    public static final int OBSTACLE_STARTING_X_POSITION = 850;

    // Starting_y_position of bottom obstacles
    public static final int BOTTOM_OBSTACLE_STARTING_Y_POSITION = 0;
}