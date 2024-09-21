package no.uib.inf101.model;
import no.uib.inf101.grid.*;

/**
 *The TetrisBoard class represents the game board for Tetris.
 *It extends the Grid class and is specialized to hold characters.
 *
 * @author  Alper.Yarenbasi
 */
public class TetrisBoard extends Grid<Character> {

    /**
     * Construct a TetrisBoard
     * @param rows
     * @param cols
     */
    public TetrisBoard(int rows, int cols) {
        super(rows, cols, '-');
    }


    /**
     * Construct a string that represents the TetrisBoard
     * @return a string that represents the TetrisBoard
     */
    public String prettyString() {
        StringBuilder sb = new StringBuilder();
        for (GridCell<Character> cell : this) {
            sb.append(cell.value());
            if (cell.pos().col() == cols() - 1 && cell.pos().row() != rows() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }


    /**
     * Method that removes a row when it is full of Character's that isn't '-'
     * @return the amount of rows removed (int)
     */
    public int removeFullRows() {
        int removedRows = 0;
        for (int row = rows() - 1; row >= 0; row--) {
            if (isRowFull(row)) {
                removeRow(row);
                removedRows++;
                row++; // Since we removed a row, we need to check the current row again

            }
        }
        return removedRows;
    }

    private boolean isRowFull(int row) {
        for (int col = 0; col < cols(); col++) {
            if (get(new CellPosition(row, col)) == '-') {
                return false;
            }
        }
        return true;
    }

    private void removeRow(int row) {
        for (int r = row; r > 0; r--) {
            for (int col = 0; col < cols(); col++) {
                set(new CellPosition(r, col), get(new CellPosition(r - 1, col)));
            }
        }
        for (int col = 0; col < cols(); col++) {
            set(new CellPosition(0, col), '-');
        }
    }



}