package no.uib.inf101.tetris.model.tetromino;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.model.tetromino.Tetromino;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestTetromino {

    @Test
    public void testHashCodeAndEquals() {
        Tetromino t1 = Tetromino.newTetromino('T');
        Tetromino t2 = Tetromino.newTetromino('T');
        Tetromino t3 = Tetromino.newTetromino('T').shiftedBy(1, 0);
        Tetromino s1 = Tetromino.newTetromino('S');
        Tetromino s2 = Tetromino.newTetromino('S').shiftedBy(0, 0);

        assertEquals(t1, t2);
        assertEquals(s1, s2);
        assertEquals(t1.hashCode(), t2.hashCode());
        assertEquals(s1.hashCode(), s2.hashCode());
        assertNotEquals(t1, t3);
        assertNotEquals(t1, s1);
    }


    @Test
    public void tetrominoIterationOfT() {
        // Create a standard 'T' tetromino placed at (10, 100) to test
        Tetromino tetro = Tetromino.newTetromino('T');
        tetro = tetro.shiftedBy(10, 100);

        // Collect which objects are iterated through
        List<GridCell<Character>> objs = new ArrayList<>();
        for (GridCell<Character> gc : tetro) {
            objs.add(gc);
        }

        // Check that we got the expected GridCell objects
        assertEquals(4, objs.size());
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 100), 'T')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 101), 'T')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 102), 'T')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 101), 'T')));
    }

    @Test
    public void tetrominoIterationOfS() {
        // Create a standard 'S' tetromino placed at (10, 100) to test
        Tetromino tetro = Tetromino.newTetromino('S');
        tetro = tetro.shiftedBy(10, 100);

        // Collect which objects are iterated through
        List<GridCell<Character>> objs = new ArrayList<>();
        for (GridCell<Character> gc : tetro) {
            objs.add(gc);
        }

        // Check that we got the expected GridCell objects
        assertEquals(4, objs.size());
        assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 100), 'S')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 101), 'S')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 101), 'S')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 102), 'S')));
    }

    @Test
    public void testShiftedByTwice() {
        // Create a standard 'I' tetromino
        Tetromino shiftedOnce = Tetromino.newTetromino('I');
        Tetromino shiftedTwice = Tetromino.newTetromino('I');

        //tetrromino shifted twice
        shiftedTwice.shiftedBy(2,2);
        shiftedTwice.shiftedBy(2,2);
        //tetromino shifted once
        shiftedOnce.shiftedBy(4,4);
        //equals the same place
        assertEquals(shiftedOnce, shiftedTwice);
    }


    @Test
    public void shiftedToTopCenterOf() {
        Tetromino tetro4x4 = Tetromino.newTetromino('I');
        Tetromino tetro3x3 = Tetromino.newTetromino('T');


        // Lagre de nye tetromino-objektene etter å ha flyttet dem til midten av rutenettet
        tetro4x4.shiftedToTopCenterOf(new Grid<>(8, 8));
        tetro3x3.shiftedToTopCenterOf(new Grid<>(5, 5));


        // Sjekk posisjonen til de nye tetromino-objektene
        assertEquals(new CellPosition(-1, 2), tetro4x4.getPosition());
        assertEquals(new CellPosition(-1, 2), tetro3x3.getPosition());
    }



}
