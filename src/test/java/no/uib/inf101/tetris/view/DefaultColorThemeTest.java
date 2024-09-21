package no.uib.inf101.tetris.view;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DefaultColorThemeTest {
    @Test
    public void sanityDefaultColorThemeTest() {
        ColorTheme colors = new DefaultColorTheme();
        assertEquals(null, colors.getBackgroundColor());
        assertEquals(new Color(0, 0, 0, 0), colors.getFrameColor());
        assertEquals(Color.BLACK, colors.getCellColor('-'));
        assertEquals(Color.RED, colors.getCellColor('S'));
        assertThrows(IllegalArgumentException.class, () -> colors.getCellColor('\n'));
    }

}
