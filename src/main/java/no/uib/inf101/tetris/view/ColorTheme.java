package no.uib.inf101.tetris.view;
import java.awt.*;

/**
 * The ColorTheme interface defines a set of colors used for rendering various elements in the Tetris game.
 * Implementations of this interface provide methods to retrieve colors for different aspects of the game,
 * @author Alper.Yarenbasi
 */
public interface ColorTheme {

    /**
     * Retrieves the color for a specific cell character.
     *
     * @param c the character representing the content of the cell
     * @return the color associated with the specified cell content
     */
    Color getCellColor(Character  c);

    /**
     * Retrieves the color for the frame or border of the game grid.
     *
     * @return the color for the frame
     */
    Color getFrameColor();

    /**
     * Retrieves the background color of the game grid.
     *
     * @return the background color of the game grid
     */
    Color getBackgroundColor();

    /**
     * Retrieves the color for the "game over" screen.
     *
     * @return the color for the "game over" screen
     */
    Color gameOverColor();

    /**
     * Retrieves the color for the "game over" message.
     *
     * @return the color for the "game over" message
     */
    Color gameOverTekstColor();

    /**
     * Retrieves the color for the 'restart' message
     *
     * @return the color for the 'restart' message
     */
    Color reStartTextColor();

    /**
     * Retrieves the color for the score counter
     *
     * @return the color for the score counter
     */
    Color scoreColor();

}
