<img align="right" width=200 src="/rmp/inGame.png">

# Tetris in Java

## Project Overview

This project is an implementation of the classic game Tetris in Java. It follows the Model-View-Controller (MVC) architecture pattern, providing a clear separation of concerns and maintainable code structure. The game features a graphical user interface, robust game logic, and background music for an immersive gaming experience.

## Project Structure

The project is organized into several packages, adhering to the MVC pattern:

<img align="right" width=200 src="/rmp/start.png">

### Model
- `no.uib.inf101.grid`: Contains classes for managing the game grid.
- `no.uib.inf101.model`: Includes the game model and tetromino-related classes.
  
### View
- `no.uib.inf101.tetris.view`: Contains classes for the game view.

### Controller
- `no.uib.inf101.tetris.controller`: Houses the game controller.

### Additional Components
- `no.uib.inf101.tetris.midi`: Includes the background music functionality.
<img align="right" width=200 src="/rmp/GameOver.png">

## Key Components

### Model
- **TetrisModel**: Represents the core game logic, managing the game state, score, and tetromino movement.
- **TetrisBoard**: Extends the Grid class and represents the game board, handling row removal and providing a string representation of the board.
- **Tetromino**: Represents a tetromino piece, including its shape, position, and rotation capabilities.
- **RandomTetrominoFactory**: Generates random tetromino shapes for gameplay variety.

### View
- **TetrisView**: Manages the graphical representation of the game, rendering the game board, current tetromino, and game state.
- **CellPositionToPixelConverter**: Helps convert grid positions to pixel coordinates for rendering.
- **ColorTheme & DefaultColorTheme**: Define the color scheme for the game elements.

### Controller
- **TetrisController**: Handles user input and game logic, including moving and rotating tetrominoes, and manages the game flow.

### Main
- **TetrisMain**: The entry point of the application that initializes the game, sets up the MVC components, and creates the game window.

## How to Run

To run the game, execute the `main` method in the `TetrisMain` class.

## Controls

- Left Arrow: Move tetromino left
- Right Arrow: Move tetromino right
- Down Arrow: Rotate tetromino counter-clockwise
- Up Arrow: Rotate tetromino clockwise
- Space: Drop tetromino

## Features

- Classic Tetris gameplay with smooth controls
- Score tracking system
- Background music for enhanced gaming experience
- Various tetromino shapes (I, J, L, O, S, T, Z)
- Three distinct game states:
  1. Welcome Screen
  2. Active Game
  3. Game Over
- Colorful and responsive user interface

## MVC Architecture

This project strictly follows the Model-View-Controller (MVC) architectural pattern:

- **Model**: Manages the game data and logic (TetrisModel, TetrisBoard, Tetromino).
- **View**: Handles the visual representation of the game state (TetrisView and related classes).
- **Controller**: Manages user input and updates the model and view accordingly (TetrisController).

This separation allows for easier maintenance, testing, and potential future enhancements.


## Future Enhancements

Potential areas for future improvement include:
- Implementing difficulty levels
- Creating additional visual themes
- Introducing multiplayer functionality
