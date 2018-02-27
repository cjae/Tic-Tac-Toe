package com.droidrank.tictactoe.util;

/**
 * Created by osagieomon on 2/27/18.
 */

public class GameTracker {

    public static int GAME_INACTIVE = 0;
    public static int GAME_ACTIVE = 1;

    private int gameState;
    private boolean isPlayerOne;

    public GameTracker() {
        this.isPlayerOne = true;
        this.gameState = GAME_INACTIVE;
    }

    public boolean isPlayerOne() {
        return isPlayerOne;
    }

    public void setPlayerOne(boolean playerOne) {
        this.isPlayerOne = playerOne;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }
}
