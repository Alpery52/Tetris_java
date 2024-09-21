package no.uib.inf101.tetris.view;

import java.awt.*;

/**
 * Class for the default Color Theme for the Tetris Game
 */
public class DefaultColorTheme implements ColorTheme{
    @Override
    public Color getCellColor(Character c) {
        Color color = switch(c) {
            case '-' -> Color.BLACK;     // Tom celle skal ha svart farge
            case 'S' -> Color.RED;
            case 'T' -> new Color(128, 0, 128);  // Lilla
            case 'L' -> new Color(255, 165, 0);  // Oransje
            case 'Z' -> Color.YELLOW;
            case 'I' -> new Color(0, 255, 255);  // Cyan
            case 'O' -> new Color(255, 192, 203); // Rosa
            case 'J' -> Color.BLUE;

            default -> throw new IllegalArgumentException(
                    "No available color for '" + c + "'");
        };
        return color;
    }


    @Override
    public Color getFrameColor() {
        return new Color(0, 0, 0, 0); // Helt gjennomsiktig ramme
    }

    @Override
    public Color getBackgroundColor() {return null;}

    @Override
    public Color gameOverColor() {return new Color(0, 0, 0, 128);}

    @Override
    public Color gameOverTekstColor() {return Color.RED;}

    @Override
    public Color reStartTextColor() {return Color.WHITE;}

    @Override
    public Color scoreColor() {return Color.RED;}
}
