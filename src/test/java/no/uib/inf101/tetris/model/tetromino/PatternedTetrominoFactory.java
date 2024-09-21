package no.uib.inf101.tetris.model.tetromino;
import no.uib.inf101.model.tetromino.Tetromino;
import no.uib.inf101.model.tetromino.TetrominoFactory;

public class PatternedTetrominoFactory implements TetrominoFactory {
    private String pattern;
    private int currentIndex;

    public PatternedTetrominoFactory(String pattern) {
        this.pattern = pattern;
        this.currentIndex = 0;
    }

    @Override
    public Tetromino getNext() {
        char symbol = pattern.charAt(currentIndex);
        currentIndex = (currentIndex + 1) % pattern.length(); // stater på start igjen hvis vi når slutt.
        return Tetromino.newTetromino(symbol);
    }
}

