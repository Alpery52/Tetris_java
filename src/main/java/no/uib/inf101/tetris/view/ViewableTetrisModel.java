package no.uib.inf101.tetris.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.model.GameState;

/**
 * The ViewableTetrisModel interface represents a view of the Tetris game model.
 * It provides methods to retrieve information about the game state, the game board, the falling tetromino,
 * and the current score.
 *
 * @author Alper.Yarenbasi
 */

public interface ViewableTetrisModel {

    /**
     * Retrieves the dimensions of the game board.
     *
     * @return the grid dimension representing the dimentions of the game board
     */
    GridDimension getDimension();

    /**
     * Retrieves the tiles currently on the game board.
     *
     * @return an iterable collection of grid cells representing the tiles on the game board
     */
    Iterable<GridCell<Character>> getTilesOnBoard();

    /**
     * Retrieves the cells occupied by the falling tetromino on the game board.
     *
     * @return an iterable collection of grid cells representing the tetromino on the game board
     */
    Iterable<GridCell<Character>> getTetrominoOnBoard();

    /**
     * Retrieves the current state of the Tetris game.
     *
     * @return a GameState object representing the current state of the Tetris game
     */
    GameState getGameState();

    /**
     * Retrieves the current score of the Tetris game.
     *
     * @return the current score of the game
     */
    int getScore();


}



