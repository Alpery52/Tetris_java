package no.uib.inf101.model.tetromino;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

import java.util.*;


/**
 * The Tetromino class represents a tetromino in the Tetris game.
 * @author Alper.Yarenbasi
 */
public final class Tetromino implements Iterable<GridCell<Character>> {

    private final char symbol;
    private final boolean[][] shape;
    private CellPosition pos;

    /**
     * Constructs a new Tetromino object
     *
     * @param symbol The symbol representing the tetromino shape.
     * @param shape The 2D array representing the shape of the tetromino.
     * @param pos The position of the tetromino on the grid.
     */
    private Tetromino(char symbol, boolean[][] shape, CellPosition pos) {
        this.symbol = symbol;
        this.shape = shape;
        this.pos = pos;
    }


    /**
     * Creates and returns a new Tetromino object with the specified symbol.
     *
     * @param symbol The symbol representing the tetromino shape.
     * @return A Tetromino object representing the specified tetromino shape.
     * @throws IllegalArgumentException If the specified symbol is unknown.
     */
    public static Tetromino newTetromino(char symbol) {
        boolean[][] shape;
        switch (symbol) {
            case 'I':
                shape = new boolean[][] {
                        { false, false, false, false},
                        {  true,  true,  true, true },
                        { false, false, false, false },
                        { false,  false, false, false }

                };
                break;
            case 'J':
                shape = new boolean[][] {
                        { false, false, false },
                        {  true,  true,  true },
                        { false,  false, true }

                };
                break;
            case 'L':
                shape = new boolean[][] {
                        { false, false, false },
                        {  true,  true,  true },
                        { true,  false, false }
                };
                break;
            case 'O':
                shape = new boolean[][] {
                        { false, false, false, false},
                        { false, true, true, false},
                        {  false,  true,  true , false},
                        { false,  false, false, false }
                };
                break;
            case 'S':
                shape = new boolean[][] {
                        { false, false, false },
                        {  false,  true,  true },
                        { true,  true, false }
                };
                break;
            case 'T':
                shape = new boolean[][] {
                        { false, false, false },
                        {  true,  true,  true },
                        { false,  true, false }
                };
                break;
            case 'Z':
                shape = new boolean[][] {
                        { false, false, false },
                        {  true,  true,  false },
                        { false,  true, true }
                };
                break;
            default:
                throw new IllegalArgumentException("Unknown tetromino symbol: " + symbol);
        }
        return new Tetromino(symbol, shape, new CellPosition(0, 0));
    }

    /**
     * Returns a new Tetromino object shifted by the specified number of rows and columns.
     *
     * @param deltaRow The number of rows to move the tetromino.
     * @param deltaCol The number of columns to move the tetromino.
     * @return A new Tetromino object shifted by the specified rows and columns.
     */
    public Tetromino shiftedBy(int deltaRow, int deltaCol) {
        CellPosition newPosition = new CellPosition(
                this.getPosition().row() + deltaRow, this.getPosition().col() + deltaCol);
        return new Tetromino(this.getSymbol(), this.getShape(), newPosition);
    }

    /**
     * Shifts the tetromino to the top center of the grid with the specified dimensions.
     *
     * @param gridDimension The dimensions of the grid.
     */
    public void shiftedToTopCenterOf(GridDimension gridDimension) {
        int numRows = gridDimension.rows();
        int numCols = gridDimension.cols();
        int middleCol = numCols / 2;
        int startCol;

        if (numCols % 2 == 0) {
            startCol = middleCol - 1;
        } else {
            startCol = middleCol;
        }

        // Beregn startkolonnen basert på formelen
        int startPosCol = startCol - (this.getShape()[0].length-2) / 2;

        this.pos = new CellPosition(-1, startPosCol);
    }



    @Override
    public Iterator<GridCell<Character>> iterator() {
        List<GridCell<Character>> cells = new ArrayList<>();
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j]) {
                    cells.add(new GridCell<>(new CellPosition(pos.row() + i, pos.col() + j), symbol));
                }
            }
        }
        return cells.iterator();
    }
    @Override
    public int hashCode() {
        return Objects.hash(symbol, Arrays.deepHashCode(shape), pos);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tetromino tetromino = (Tetromino) obj;
        return symbol == tetromino.symbol &&
                Arrays.deepEquals(shape, tetromino.shape) &&
                Objects.equals(pos, tetromino.pos);
    }

    /**
     * Rotates the tetromino clockwise.
     *
     * @return A new tetromino rotated clockwise.
     */
    public Tetromino rotateClockwise() {
        // Finn dimensjonene til den originale tetrominoen
        int rows = shape.length;
        int cols = shape[0].length;

        // Opprett en ny matrise for den roterte tetrominoen
        boolean[][] rotatedShape = new boolean[cols][rows];

        // Gå gjennom hvert element i den roterte matrisen og sett verdien
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                rotatedShape[c][rows - 1 - r] = shape[r][c];
            }
        }

        // Opprett og returner den roterte tetrominoen med den nye matrisen
        return new Tetromino(symbol, rotatedShape, pos);
    }

    /**
     * Rotates the tetromino counter clockwise.
     *
     * @return A new tetromino rotated counter clockwise.
     */
    public Tetromino rotateCounterClockwise() {
        // Finn dimensjonene til den originale tetrominoen
        int rows = shape.length;
        int cols = shape[0].length;

        // Opprett en ny matrise for den roterte tetrominoen
        boolean[][] rotatedShape = new boolean[cols][rows];

        // Gå gjennom hvert element i den roterte matrisen og sett verdien
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                rotatedShape[cols - 1 - c][r] = shape[r][c];
            }
        }

        // Opprett og returner den roterte tetrominoen med den nye matrisen
        return new Tetromino(symbol, rotatedShape, pos);
    }






    /**
     * getter method for symbol
     *
     * @return the symbol for the tetromino
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * getter method for shape
     *
     * @return the symbol for the shape
     */
    public boolean[][] getShape() {
        return shape;
    }

    /**
     * getter method for position
     *
     * @return the symbol for the position
     */
    public CellPosition getPosition() {
        return pos;
    }
}

