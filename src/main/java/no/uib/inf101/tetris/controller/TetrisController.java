package no.uib.inf101.tetris.controller;

import no.uib.inf101.model.GameState;
import no.uib.inf101.tetris.midi.TetrisSong;
import no.uib.inf101.tetris.view.TetrisView;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

/**
 * The TetrisController class represents the controller component in the Tetris game
 * It handles user input, updates the game model, and triggers updates to the view accordingly.
 * @author Alper.Yarenbasi
 */
public class TetrisController implements java.awt.event.KeyListener {

    private ControllableTetrisModel tetrisModel; // The Tetris game model
    private TetrisView tetrisView; // The Tetris game view
    private Timer timer; // Timer for controlling the game loop
    private TetrisSong backgroundSong; // Background music for the game


    /**
     * Constructs a new TetrisController with the specified controllable model and view.
     *
     * @param controllableModel the controllable Tetris model
     * @param tetrisView the Tetris game view
     */
    public TetrisController(ControllableTetrisModel controlableModel, TetrisView tetrisView){
        this.tetrisModel = controlableModel;
        this.tetrisView = tetrisView;
        tetrisView.addKeyListener(this);
        this.timer = new Timer(controlableModel.getTimerDelay(), this::clockTick);
        this.backgroundSong = new TetrisSong();
        backgroundSong.run();


    }


    @Override
    public void keyPressed(KeyEvent e) {
        // Handling key events based on the current game state
        // Welcome Screen
        if (tetrisModel.getGameState() == GameState.WELCOME_SCREEN) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                startTetrisGame();
            }
        }
        // Active Game
        else if (tetrisModel.getGameState() == GameState.ACTIVE_GAME) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                this.tetrisModel.moveTetromino(0, -1);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                this.tetrisModel.moveTetromino(0, 1);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                this.tetrisModel.rotateTetrominoCounterClockwise();
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                this.tetrisModel.rotateTetrominoClockwise();
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                this.tetrisModel.dropTetromino();
                timer.restart();
            }
            tetrisView.repaint();
        }
        // Game over state of game
        else if (tetrisModel.getGameState() == GameState.GAME_OVER) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                restartGame();
            }
        }
    }
    private void updateTimerDelay() {
        int delay = this.tetrisModel.getTimerDelay();
        timer.setDelay(delay);
        timer.setInitialDelay(delay);
    }


    private void clockTick(ActionEvent e) {
        if (this.tetrisModel.getGameState() == GameState.ACTIVE_GAME) {
            this.tetrisModel.clockTick();
            updateTimerDelay();
            this.tetrisView.repaint();
        }
    }

    private void restartGame() {
        this.tetrisModel.resetGame();
        this.tetrisView.repaint(); // Oppdater visningen
    }

    private void startTetrisGame() {
        this.tetrisModel.startGame();
        timer.start(); // Start timeren når spillet starter
        this.tetrisView.repaint(); // Oppdater visningen
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
