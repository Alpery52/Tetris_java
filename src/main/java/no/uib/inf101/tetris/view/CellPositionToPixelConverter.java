package no.uib.inf101.tetris.view;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;
import java.awt.geom.Rectangle2D;

/**
 * The CellPositionToPixelConverter class converts cell positions to pixel coordinates
 * within a given box.
 *
 * @author Alper.Yarenbasi
 */
public class CellPositionToPixelConverter {
    private Rectangle2D box;
    private GridDimension gd;
    private double margin;


    /**
     * Constructs a CellPositionToPixelConverter with the specified parameters.
     *
     * @param box    the bounding box for the grid
     * @param gd     the grid dimension (number of rows and columns)
     * @param margin the margin between cells
     */
    public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin){
        this.box = box;
        this.gd = gd;
        this.margin = margin;
    }

    /**
     * Calculates the bounds (rectangle) for a cell at the specified position.
     *
     * @param pos the position of the cell
     * @return the bounds (rectangle) for the cell
     */
    public Rectangle2D getBoundsForCell(CellPosition pos){
        double boxTopX = this.box.getX();
        double boxTopY = this.box.getY();
        double boxWidth = this.box.getWidth();
        double boxHight = this.box.getHeight();
        double col = pos.col();
        double row = pos.row();
        double cols = this.gd.cols();
        double rows = this.gd.rows();
        double margin = this.margin;

        double cellWidth = (boxWidth- (cols+1)*margin)/cols;
        double cellHeight = (boxHight- (rows+1)*margin)/rows;

        double cellX = boxTopX+ col*cellWidth + (col+1)*margin;
        double cellY = boxTopY + row*cellHeight + (row+1)*margin;

        return new Rectangle2D.Double(cellX, cellY, cellWidth, cellHeight);
    }
}
