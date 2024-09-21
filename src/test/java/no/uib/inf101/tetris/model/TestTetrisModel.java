package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.model.TetrisBoard;
import no.uib.inf101.model.TetrisModel;
import no.uib.inf101.model.tetromino.Tetromino;
import no.uib.inf101.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.PatternedTetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestTetrisModel {




    @Test
    public void initialPositionOfO() {
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("O");
        ViewableTetrisModel model = new TetrisModel(board, factory);

        List<GridCell<Character>> tetroCells = new ArrayList<>();
        for (GridCell<Character> gc : model.getTetrominoOnBoard()) {
            tetroCells.add(gc);
        }

        assertEquals(4, tetroCells.size());
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'O')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'O')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 4), 'O')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 5), 'O')));
    }

    @Test
    public void initialPositionOfI() {
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("I");
        ViewableTetrisModel model = new TetrisModel(board, factory);


        List<GridCell<Character>> tetroCells = new ArrayList<>();
        for (GridCell<Character> gc : model.getTetrominoOnBoard()) {
            tetroCells.add(gc);
        }

        assertEquals(4, tetroCells.size());
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 3), 'I')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'I')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'I')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 6), 'I')));
    }

    @Test
    public void testSuccessfulMoveReturnsTrue() {
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("O");
        TetrisModel model = new TetrisModel(board, factory);

        assertTrue(model.moveTetromino(1, 0));
    }

    @Test
    public void testMoveOutOfBoardReturnsFalse() {
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("O");
        TetrisModel model = new TetrisModel(board, factory);

        // Move the tetromino out of the board boundaries
        assertFalse(model.moveTetromino(100, 10));
    }

    @Test
    public void testMoveToOccupiedPositionReturnsFalse() {
        TetrisBoard board = new TetrisBoard(20, 5);
        TetrominoFactory factory = new PatternedTetrominoFactory("O");
        TetrisModel model = new TetrisModel(board, factory);

        //occupied positions
        board.set(new CellPosition(3, 2), 'g');
        board.set(new CellPosition(3, 3), 'g');

        // trying to move to occupied position
        assertFalse(model.moveTetromino(3, 0));
    }

    @Test
    public void rotationClockwiseT(){
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("T");
        TetrisModel model = new TetrisModel(board, factory);

        // Beveg tetrominoen slik at vi kan rotere den fritt
        model.moveTetromino(3, 2);

        // Hent den roterte tetrominoen etter rotasjon
        model.rotateTetrominoClockwise();
        Tetromino rotatedTetromino = (Tetromino) model.getTetrominoOnBoard();
        rotatedTetromino.rotateClockwise();

        // Forventet rotert form
        boolean[][] expectedShape = new boolean[][] {
                { false, true, false },
                { true, true, false },
                { false, true, false }
        };

        // Sjekk om den roterte formen matcher det forventede
        assertArrayEquals(expectedShape, rotatedTetromino.getShape());
    }


    @Test
    public void cantRotateOut() {
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("T");
        TetrisModel model = new TetrisModel(board, factory);

        // prøver å rotere tetroino men siden det er i row 0, så kan den ikke rotere fordi ellers så kommer den ut
        model.rotateTetrominoClockwise();
        assertFalse(model.rotateTetrominoClockwise());

    }

    @Test
    public void testDropTetrominoT() {
        TetrisBoard board = new TetrisBoard(10, 5);
        TetrominoFactory factory = new PatternedTetrominoFactory("T");
        TetrisModel model = new TetrisModel(board, factory);

        model.dropTetromino();
        String boardString = board.prettyString();
        //For å manuelt se at tetroiono er droppet
        //System.out.println(boardString);

        String[] rows = boardString.split("\n"); // Deler strengen inn i rader ved linjeskift
        String lastRow = rows[rows.length - 1]; // Henter den siste raden

        // Sjekker om den siste raden inneholder en 'T'
        assertTrue(lastRow.contains("T"));

    }

    @Test
    public void clockTickTest(){
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("I");
        TetrisModel model = new TetrisModel(board, factory);

        model.clockTick();

        List<GridCell<Character>> tetroCells = new ArrayList<>();
        for (GridCell<Character> gc : model.getTetrominoOnBoard()) {
            tetroCells.add(gc);
        }
        //Checking to see if the tetromino has moved a row down
        assertEquals(4, tetroCells.size());
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 3), 'I')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 4), 'I')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 5), 'I')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 6), 'I')));
    }


    @Test
    public void clockTickTestTwice(){
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("I");
        TetrisModel model = new TetrisModel(board, factory);

        model.clockTick();
        model.clockTick();

        List<GridCell<Character>> tetroCells = new ArrayList<>();
        for (GridCell<Character> gc : model.getTetrominoOnBoard()) {
            tetroCells.add(gc);
        }
        //Checking to see if the tetromino has moved 2 rows down
        assertEquals(4, tetroCells.size());
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(2, 3), 'I')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(2, 4), 'I')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(2, 5), 'I')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(2, 6), 'I')));
    }

    /* GJØR FERDIG SENERE
    @Test
    public void clockTickTestOutOfGrid(){
        TetrisBoard board = new TetrisBoard(3, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("I");
        TetrisModel model = new TetrisModel(board, factory);

        model.clockTick();
        model.clockTick();
        model.clockTick();

        List<GridCell<Character>> tetroCells = new ArrayList<>();
        for (GridCell<Character> gc : model.getTetrominoOnBoard()) {
            tetroCells.add(gc);
        }
        for(GridCell<Character> gc : tetroCells){
            System.out.println(gc);
        }
        int bottom = 3;
        //Checking to see if the tetromino has moved 2 rows down
        assertEquals(4, tetroCells.size());
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(2, 3), 'I')));
        //assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(bottom, 4), 'I')));
        //assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(bottom, 5), 'I')));
        //assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(bottom, 6), 'I')));
    }

     */






}
