package se.yrgo.flappybird;

public class Constants {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final float PLAYER_STARTING_X_POSITION_CENTERED = (float) Constants.getWidth() / 2 - (float) GameScreen.getPlayerWidth() / 2;

    public static float getPLAYER_STARTING_X_POSITION_CENTERED() {
        return PLAYER_STARTING_X_POSITION_CENTERED;
    }

    public static int getWidth() {
        return WIDTH;
    }

    public static int getHeight() {
        return HEIGHT;
    }
}