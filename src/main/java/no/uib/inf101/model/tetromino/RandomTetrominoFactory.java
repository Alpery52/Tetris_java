package no.uib.inf101.model.tetromino;
import java.util.Random;

/**
 * The RandomTetrominoFactory class implements the TetrominoFactory interface
 * and provides functionality to generate random tetromino shapes.
 *
 * @author Alper.Yarenbasi
 */
public class RandomTetrominoFactory implements TetrominoFactory{

    /**
     * Generates and returns the next random tetromino shape.
     *
     * @return A Tetromino object representing the next random tetromino shape.
     */
    @Override
    public Tetromino getNext() {
        String TetroShapes = "LJSZTIO";
        Random random = new Random();

        // Generer et tilfeldig tall mellom 0 og lengden på ordet minus 1
        int randomIndex = random.nextInt(TetroShapes.length());

        // Velg den tilfeldige bokstaven fra ordet
        char randomShape = TetroShapes.charAt(randomIndex);

        return Tetromino.newTetromino(randomShape);
    }
}
