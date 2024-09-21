package no.uib.inf101.tetris.view;

import no.uib.inf101.grid.*;
import no.uib.inf101.model.GameState;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static no.uib.inf101.tetris.view.Inf101Graphics.drawCenteredString;

public class TetrisView  extends JPanel {
    private ViewableTetrisModel tetrisModel;
    private BufferedImage startImage;
    private ColorTheme colorTheme;
    static final double OUTERMARGIN = 20;
    static final double INNERMARGIN = 2;
    static final double DESIREDSIZE = 35;

    /**
     * Constructs a TetrisView with the specified Tetris model.
     *
     * @param tetrisModel the Tetris model to be displayed
     */
    public TetrisView(ViewableTetrisModel tetrisModel) {
        this.setFocusable(true);
        this.tetrisModel = tetrisModel;
        this.colorTheme = new DefaultColorTheme(); // Initialize color theme

        // Set background color
        Color backgroundColor = colorTheme.getBackgroundColor();
        this.setBackground(backgroundColor);
        int desiredWidth = desiredWidth();
        int desiredHeight = desiredHeight();
        this.setPreferredSize(new Dimension(desiredWidth, desiredHeight));

        startImage = Inf101Graphics.loadImageFromResources("startScreenImage.png");


    }

    // The paintComponent method is called by the Java Swing framework every time
    // either the window opens or resizes, or we call .repaint() on this object.
    // Note: NEVER call paintComponent directly yourself
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (tetrisModel.getGameState() == GameState.WELCOME_SCREEN) {
            // draw StartScreen
            drawStartScreen(g2);
        } else {
            drawGame(g2);
            drawGameScoreInGame(g2);
            gameOver(g2);
        }
    }

    private void drawGame(Graphics2D g2) {
        double x = OUTERMARGIN;
        double y = OUTERMARGIN;
        double width = this.getWidth() - 2 * OUTERMARGIN;
        double height = this.getHeight() - 2 * OUTERMARGIN;

        g2.setColor(colorTheme.getFrameColor());
        Rectangle2D outerBox = new Rectangle2D.Double(x, y, width, height);
        g2.fill(outerBox);

        CellPositionToPixelConverter posConverter = new CellPositionToPixelConverter(
                outerBox, tetrisModel.getDimension(), INNERMARGIN
        );

        Iterable<GridCell<Character>> cells = tetrisModel.getTilesOnBoard();
        drawCells(g2, cells, posConverter, colorTheme);
        drawCells(g2, tetrisModel.getTetrominoOnBoard(), posConverter, colorTheme);

    }

    private static void drawCells(
            Graphics2D g2,
            Iterable<GridCell<Character>> cells,
            CellPositionToPixelConverter posConverter,
            ColorTheme colorTheme
    ) {

        for (GridCell<Character> cell : cells) {
            Rectangle2D rec = posConverter.getBoundsForCell(cell.pos());
            Color color = colorTheme.getCellColor(cell.value());
            g2.setColor(color);
            g2.fill(rec);
        }
    }

    private void drawGameScoreInGame(Graphics2D g2) {
        int score = tetrisModel.getScore();
        String scoreText = "Score: " + score;
        g2.setColor(colorTheme.scoreColor());
        g2.setFont(new Font("Arial", Font.PLAIN, 16));
        g2.drawString(scoreText, 20, getHeight() - (getHeight()-15));
    }
    private int desiredWidth() {
        return (int) ((DESIREDSIZE + INNERMARGIN) * tetrisModel.getDimension().cols()
                + INNERMARGIN + OUTERMARGIN);
    }

    private int desiredHeight() {
        return (int) ((DESIREDSIZE + INNERMARGIN) * tetrisModel.getDimension().rows()
                + INNERMARGIN + OUTERMARGIN);
    }

    private void gameOver(Graphics2D g2) {
        if (tetrisModel.getGameState() == GameState.GAME_OVER) {
            g2.setColor(colorTheme.gameOverColor());
            g2.fillRect(0, 0, getWidth(), getHeight());


            drawGameOverText(g2);   // Draw gameover text
            drawFinalGameScore(g2); // Draw final game score when game over
            drawRestartMessage(g2); // Draw restart message when game over
        }

    }

    private void drawRestartMessage(Graphics2D g2) {
        String message = "PRESS SPACE TO RESTART";
        g2.setColor(colorTheme.reStartTextColor());
        g2.setFont(new Font("Arial", Font.PLAIN, 18));
        drawCenteredString(g2, message, getWidth() / 2, getHeight() / 2 + 70);
    }

    private void drawFinalGameScore(Graphics2D g2) {
        int score = tetrisModel.getScore();
        String scoreText = "Score: " + score;
        g2.setFont(new Font("Arial", Font.PLAIN, 24));
        drawCenteredString(g2, scoreText, getWidth() / 2, getHeight() / 2 + 40);

    }
    private void drawGameOverText(Graphics2D g2) {
        g2.setColor(colorTheme.gameOverTekstColor());
        g2.setFont(new Font("Arial", Font.BOLD, 36));
        String gameOverText = "Game Over";
        drawCenteredString(g2, gameOverText, getWidth() / 2, getHeight() / 2);
    }
    private void drawStartScreen(Graphics2D g2) {
        if (this.startImage != null) {
            g2.drawImage(this.startImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }

    }


}