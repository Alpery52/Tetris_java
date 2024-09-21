package no.uib.inf101.grid;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Iterator;

/**
 * Constructs a new grid with specified dimensions and default value.
 * The grid consists of cells that can hold elements of type E.
 *
 * @param <E> The type of elements held in the grid cells.
 * @author Alper.Yarenbasi
 */
public class Grid<E> implements IGrid<E> {

    private final int rows;
    private final int cols;
    private final E defaultValue;
    private List<List<GridCell<E>>> gridArray;

    /**
     * Constructs a new grid with the specified number of rows, columns, and default value.
     *
     * @param rows         The number of rows in the grid.
     * @param cols         The number of columns in the grid.
     * @param defaultValue The default value to initialize each cell with.
     */
    public Grid(int rows, int cols, E defaultValue) {
        this.rows = rows;
        this.cols = cols;
        this.defaultValue = defaultValue;

        gridArray = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<GridCell<E>> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(new GridCell<>(new CellPosition(i, j), defaultValue));
            }
            gridArray.add(row);

        }
    }

    /**
     * Constructs a new grid with the specified number of rows and columns,
     * and initializes each cell with null.
     *
     * @param rows The number of rows in the grid.
     * @param cols The number of columns in the grid.
     */
    public Grid(int rows, int cols) {
        this(rows, cols, null);
    }



    /**
     * Sets the value of the cell at the specified position.
     *
     * @param pos   a CellPosition object representing the position of the cell
     * @param value The value to set.
     * @throws IndexOutOfBoundsException if the specified position is outside the grid boundaries.
     */
    @Override
    public void set(CellPosition pos, E value) {
        if (!positionIsOnGrid(pos)) {
            throw new IndexOutOfBoundsException("Coordinate is off the grid");
        }
        gridArray.get(pos.row()).set(pos.col(), new GridCell<>(pos, value));
    }

    /**
     * Retrieves the value of the cell at the specified position.
     *
     * @param pos a CellPosition object representing the position of the cell
     * @return The value of the cell at the specified position.
     * @throws IndexOutOfBoundsException if the specified position is outside the grid boundaries.
     */
    @Override
    public E get(CellPosition pos) {
        if (!positionIsOnGrid(pos)) {
            throw new IndexOutOfBoundsException("Coordinate is off the grid");
        }
        return gridArray.get(pos.row()).get(pos.col()).value();
    }


    /**
     * Checks if the specified position is within the grid boundaries.
     *
     * @param pos The position to check.
     * @return true if the position is within the grid boundaries, false otherwise.
     */
    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        int row = pos.row();
        int col = pos.col();
        return row >= 0 && row <= this.rows && col >= 0 && col <= this.cols;
    }

    /**
     * Returns an iterator over the grid cells.
     *
     * @return An iterator over the grid cells.
     */
    @Override
    public Iterator<GridCell<E>> iterator() {
        // Returnerer en iterator for hele gridArray
        List<GridCell<E>> cells = new ArrayList<>();
        for (List<GridCell<E>> row : gridArray) {
            cells.addAll(row);
        }
        return cells.iterator();
    }

    /**
     * Returns the number of rows in the grid.
     *
     * @return The number of rows in the grid.
     */
    @Override
    public int rows() {return this.rows;}

    /**
     * Returns the number of columns in the grid.
     *
     * @return The number of columns in the grid.
     */
    @Override
    public int cols() {return this.cols;}


    /**
     * Checks if the specified row and column valid.
     *
     * @param row The row index.
     * @param col The column index.
     * @return true if the indices are valid, false otherwise.
     * En liten forskjell fra den over. Hindret meg i å få IndexOutOfBoundsException
     */
    public boolean isValidPosition(int row, int col) {
        return (row >= 0 && row < this.rows) && (col >= 0 && col < this.cols);
    }

    /**
     * Clears all cells in the grid and sets them to the specified value.
     *
     * @param value The value to set for all cells in the grid.
     */
    public void clear(E value) {
        for (GridCell<E> cell : this) {
            set(cell.pos(), value);
        }
    }
}
