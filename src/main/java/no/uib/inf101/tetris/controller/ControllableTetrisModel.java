package no.uib.inf101.tetris.controller;

import no.uib.inf101.model.GameState;

/**
 * The ControllableTetrisModel interface represents a controllable model of the tetris game.
 * It provides methods to control the movement and behavior of the falling tetromino, as well as
 * game state management functions such as starting and resetting the game.
 * @author Alper.Yarenbasi
 */
public interface ControllableTetrisModel {

    /**
     * Moves the falling tetromino by the specified delta row and column.
     *
     * @param deltaRow the change in row position
     * @param deltaCol the change in column position
     * @return true if the tetromino was successfully moved, false otherwise
     */
    boolean moveTetromino(int deltaRow, int deltaCol);

    /**
     * Rotates the falling tetromino clockwise.
     *
     * @return true if the tetromino was successfully rotated, false otherwise
     */
    boolean rotateTetrominoClockwise();

    /**
     * Rotates the falling tetromino counter-clockwise.
     *
     * @return true if the tetromino was successfully rotated, false otherwise
     */
    boolean rotateTetrominoCounterClockwise();

    /**
     * Drops the falling tetromino to the lowest possible position.
     */
    void dropTetromino();


    /**
     * Retrieves the current state of the Tetris game.
     *
     * @return the game state
     */
    GameState getGameState();

    /**
     * Retrieves the delay between clock ticks, used for game timing.
     *
     * @return the delay between clock ticks in milliseconds
     */
    int getTimerDelay();

    /**
     * Moved a falling tetromino down a row every clock tick
     */
    void clockTick();

    /**
     * Resets the Tetris game
     */
    void resetGame();

    /**
     * starts the Tetris game
     */
    void startGame();
}
