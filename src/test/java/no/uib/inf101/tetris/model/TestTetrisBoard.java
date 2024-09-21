package no.uib.inf101.tetris.model;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.model.TetrisBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestTetrisBoard {

    private TetrisBoard getTetrisBoardWithContents(String[] contents) {
        int rows = contents.length;
        int cols = contents[0].length();
        TetrisBoard board = new TetrisBoard(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                char value = contents[row].charAt(col);
                board.set(new CellPosition(row, col), value);
            }
        }
        return board;
    }
    @Test
    public void prettyStringTest() {
        TetrisBoard board = new TetrisBoard(3, 4);
        board.set(new CellPosition(0, 0), 'g');
        board.set(new CellPosition(0, 3), 'y');
        board.set(new CellPosition(2, 0), 'r');
        board.set(new CellPosition(2, 3), 'b');
        String expected = String.join("\n", new String[] {
                "g--y",
                "----",
                "r--b"
        });
        assertEquals(expected, board.prettyString());
    }


    @Test
    public void testRemoveFullRows() {
        TetrisBoard board = getTetrisBoardWithContents(new String[] {
                "-T",
                "TT",
                "LT",
                "L-",
                "LL"
        });
        assertEquals(3, board.removeFullRows());
        String expected = String.join("\n", new String[] {
                "--",
                "--",
                "--",
                "-T",
                "L-"
        });
        assertEquals(expected, board.prettyString());
    }






}
