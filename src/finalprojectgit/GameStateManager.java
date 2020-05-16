package finalprojectgit;

import java.awt.Graphics;

/**
 *
 * @author ricar
 */
public class GameStateManager {

    private boolean paused;
    private PauseState pauseState;
    private GameState[] gameStates;

    private int currentState;
    private int previousState;

    public static final int NUM_STATES = 7;
    public static final int INTRO = 0;
    public static final int MENU = 1;
    public static final int LOAD = 2;
    public static final int LEVEL1 = 3;
    public static final int LEVEL2 = 4;
    public static final int LEVEL3 = 5;
    public static final int GAMEOVER = 6;

    public GameStateManager() {
        paused = false;
        pauseState = new PauseState(this);

        gameStates = new GameState[NUM_STATES];
        setState(MENU);
    }

    public void setState(int state) {
        previousState = currentState;
        unloadState(previousState);
        currentState = state;
        if (state == INTRO) {
            gameStates[state] = new IntroState(this);
            gameStates[state].init();
        } else if (state == MENU) {
            gameStates[state] = new MenuState(this);
            gameStates[state].init();
        } else if (state == LOAD) {
            gameStates[state] = new LoadState(this);
            gameStates[state].init();
        } else if (state == LEVEL1) {
            gameStates[state] = new Level1(this);
            gameStates[state].init();
        } else if (state == LEVEL2) {
            gameStates[state] = new Level2(this);
            gameStates[state].init();
        } else if (state == LEVEL3) {
            gameStates[state] = new Level3(this);
            gameStates[state].init();
        } else if (state == GAMEOVER) {
            gameStates[state] = new GameOverState(this);
            gameStates[state].init();
        }
    }

    public void unloadState(int i) {
        gameStates[i] = null;
    }

    public void setPaused(boolean b) {
        paused = b;
    }

    public void tick() {
        if (paused) {
            pauseState.tick();
        } else if (gameStates[currentState] != null) {
            gameStates[currentState].tick();
        }
    }

    public void render(Graphics g) {
        if (paused) {
            pauseState.render(g);
        } else if (gameStates[currentState] != null) {
            gameStates[currentState].render(g);
        }
    }
}