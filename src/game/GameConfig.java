package game;

public final class GameConfig {

    private GameConfig() {} // prevent instantiation

    // Map
    public static final int TILE_SIZE   = 40;
    public static final int FOOD_SIZE   = 7;
    public static final int MAP_COLS    = 10;
    public static final int MAP_ROWS    = 10;

    // Player
    public static final int PLAYER_SIZE  = 32;
    public static final int PLAYER_SPEED  = 4;
    public static final int DEFAULT_LIVES = 3;

    // Ghost
    public static final int GHOST_SPEED = 2;
    public static final int GHOST_SIZE  = 40;
    public static final int GHOST_TURN_CHANCE = 3;

    // Game loop
    public static final int FPS = 60;
}