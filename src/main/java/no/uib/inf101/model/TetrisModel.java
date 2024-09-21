package no.uib.inf101.model;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.model.tetromino.RandomTetrominoFactory;
import no.uib.inf101.model.tetromino.Tetromino;
import no.uib.inf101.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.*;
import no.uib.inf101.tetris.controller.ControllableTetrisModel;

/**
 * The TetrisModel class represents the game model for Tetris.
 * It implements both the ViewableTetrisModel and ControllableTetrisModel interfaces.
 * @author Alper.Yarenbasi
 */
public class TetrisModel implements ViewableTetrisModel, ControllableTetrisModel {

    private final TetrisBoard board;
    private final TetrominoFactory factory;
    private Tetromino fallingTetromino;
    private GameState currentGameState;
    private int gameScore;

    /**
     * Constructs a TetrisModel with the specified TetrisBoard and TetrominoFactory.
     *
     * @param board   the TetrisBoard representing the game board
     * @param factory the TetrominoFactory to generate Tetrominoes
     */
    public TetrisModel(TetrisBoard board, TetrominoFactory factory) {
        this.board = board;
        this.factory = factory;
        this.currentGameState = GameState.WELCOME_SCREEN;
        this.gameScore = 0;

        this.fallingTetromino = factory.getNext();
        this.fallingTetromino.shiftedToTopCenterOf(board);

    }

    @Override
    public GridDimension getDimension() {
        return board;
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return board;
    }

    @Override
    public Iterable<GridCell<Character>> getTetrominoOnBoard() {
        return this.fallingTetromino;
    }

    @Override
    public GameState getGameState() {return currentGameState;}

    @Override
    public int getScore() {
        return gameScore;
    }

    @Override
    public void resetGame() {
        // reset board
        this.board.clear('-');

        // reset game score
        this.gameScore = 0;

        // update game state to active
        this.currentGameState = GameState.ACTIVE_GAME;

        // Gets a new falling tetromino
        newFallingTetromino();
    }

    @Override
    public void startGame() {
        this.currentGameState = GameState.ACTIVE_GAME;
    }


    @Override
    public int getTimerDelay() {
        return 1000;
    }

    @Override
    public void clockTick() {
        if (!moveTetromino(1,0)){
            lockTetromino(this.fallingTetromino);
        }
    }

    @Override
    public boolean moveTetromino(int deltaRow, int deltaCol) {
        Tetromino candidate = this.fallingTetromino.shiftedBy(deltaRow, deltaCol);
        if (isPositionValid(candidate)) {
            this.fallingTetromino = candidate;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean rotateTetrominoClockwise() {
        Tetromino candidate = this.fallingTetromino.rotateClockwise();
        if (isPositionValid(candidate)) {
            this.fallingTetromino = candidate;
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean rotateTetrominoCounterClockwise() {
        Tetromino candidate = this.fallingTetromino.rotateCounterClockwise();
        if (isPositionValid(candidate)) {
            this.fallingTetromino = candidate;
            return true;
        }else {
            return false;
        }
    }


    @Override
    public void dropTetromino() {
        while (moveTetromino(1, 0)) {
            // keeps moving tetromino down as long as possible
        }
        lockTetromino(this.fallingTetromino);
    }

    private void lockTetromino(Tetromino currentTetromino) {
        // lock tetromino to board
        for (GridCell<Character> cell : currentTetromino) {
            board.set(cell.pos(), currentTetromino.getSymbol());
        }

        int rowsRemoved = board.removeFullRows();
        updateScore(rowsRemoved);
        // New falling tetromino
        newFallingTetromino();

        if (!isPositionValid(this.fallingTetromino)) {
            currentGameState = GameState.GAME_OVER;
        }
    }


    private boolean isPositionValid(Tetromino tetromino) {
        for (GridCell<Character> cell : tetromino) {
            int row = cell.pos().row();
            int col = cell.pos().col();

            if (!board.isValidPosition(row, col) || board.get(cell.pos()) != '-') {
                return false;
            }
        }
        return true;
    }

    private void updateScore(int removedRows) {
        //callculate points earned
        int pointsEarned = calculateScore(removedRows);
        // updates the score
        gameScore += pointsEarned;
    }

    private int calculateScore(int removedRows) {
        int baseScore = 0;
        if (removedRows >= 4) {
            baseScore = 800;
        } else {
            switch (removedRows) {
                case 1:
                    baseScore = 100;
                    break;
                case 2:
                    baseScore = 300;
                    break;
                case 3:
                    baseScore = 500;
                    break;
                default:
                    break;
            }
        }
        return baseScore;
    }
    private void newFallingTetromino(){
        this.fallingTetromino = factory.getNext();
        this.fallingTetromino.shiftedToTopCenterOf(board);
    }
}





