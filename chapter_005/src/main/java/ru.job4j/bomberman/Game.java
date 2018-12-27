package ru.job4j.bomberman;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import ru.job4j.chessboard.Cell;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This class is game.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Game {
    /**
     * Board size X x Y.
     */
    private int boardSize;
    /**
     * Count of monsters.
     */
    private int countMonsters;
    /**
     * Count of obstacles;
     */
    private int countObstacles;
    /**
     * Hero.
     */
    private Hero bomberMan;
    /**
     * Start cell of hero.
     */
    private final Cell bomberManCellStart = new Cell(0, 0);
    /**
     * Shape of hero.
     */
    private final Circle bomberManShape = new Circle(bomberManCellStart.getX(), bomberManCellStart.getY(), 5);
    /**
     * Shapes of monsters.
     */
    private final ArrayList<Circle> monsters = new ArrayList<>(countMonsters);
    /**
     * Shapes of obstacles.
     */
    private final ArrayList<Rectangle> obstacles = new ArrayList<>(countObstacles);
    /**
     * Game board.
     */
    private Board board;
    /**
     * Pool Threads.
     */
    private ExecutorService pool;

    /**
     * Add monsters, obstacles and hero.
     */
    private void addMonstersAndObstacles() {
        Set<Cell> setOfStartCell = new HashSet<>();
        setOfStartCell.add(bomberManCellStart);
        while (setOfStartCell.size() < this.countMonsters + this.countObstacles + 1) {
            setOfStartCell.add(randomCell(boardSize));
        }
        setOfStartCell.remove(bomberManCellStart);
        setOfStartCell.stream().limit(this.countMonsters).forEach(cell -> {
            Circle circle = new Circle(cell.getX() * 20, cell.getY() * 20, 5);
            monsters.add(circle);
            pool.submit(new Monster(board, cell, circle));
        });
        setOfStartCell.stream().skip(this.countMonsters).forEach(cell -> {
            obstacles.add(new Rectangle(cell.getX() * 20, cell.getY() * 20, 10, 10));
            this.board.getReentrantLock(cell).lock();
        });
        bomberMan = new Hero(this.board, bomberManCellStart, bomberManShape);
        this.board.getReentrantLock(bomberManCellStart).lock();
    }

    /**
     * Create a cell with a random coordinate, within the size of the board.
     * @param sizeOfBoard size of game board.
     * @return Cell.
     */
    private Cell randomCell(int sizeOfBoard) {
        Random random = new Random();
        return new Cell(random.nextInt(sizeOfBoard), random.nextInt(sizeOfBoard));
    }

    /**
     * Create game.
     * @param boardSize board size.
     * @param countMonsters count of monsters.
     * @param countObstacles count of obstacles.
     */
    protected void createGame(int boardSize, int countMonsters, int countObstacles) {
        this.boardSize = boardSize;
        this.countMonsters = countMonsters;
        this.countObstacles = countObstacles;
        this.board = new Board(this.boardSize);
        this.pool = Executors.newFixedThreadPool(countMonsters);
        addMonstersAndObstacles();
    }

    /**
     * Get difficulty.
     * @return number [1;9].
     */
    protected int getDifficulty() {
        System.out.println("Choose game difficulty from 1 - 9");
        int difficult = 0;
        while (difficult <= 0 || difficult > 9) {
            Scanner sc = new Scanner(System.in);
            try {
                difficult = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter the number.");
            }
            if (difficult <= 0 || difficult > 9) {
                System.out.println("Please enter number from 1 to 9.");
            }
        }
        return difficult;
    }

    /**
     * Shutdown pool and await termination.
     */
    protected void shutdownPoolAndAwaitTermination() {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(3, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                if (!pool.awaitTermination(3, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Getter for boardSize.
     * @return board size.
     */
    protected int getBoardSize() {
        return boardSize;
    }

    /**
     * Getter for bomberManShape.
     * @return bomberManShape.
     */
    protected Circle getBomberManShape() {
        return bomberManShape;
    }

    /**
     * Getter for monsters shapes.
     * @return monster shapes.
     */
    protected ArrayList<Circle> getMonsters() {
        return monsters;
    }

    /**
     * Getter for obstacle shapes.
     * @return obstacle shapes.
     */
    protected ArrayList<Rectangle> getObstacles() {
        return obstacles;
    }
}
