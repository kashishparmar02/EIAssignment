package rover;

import grid.Grid;

public class Rover {
    private int x;
    private int y;
    private Direction direction;
    private final Grid grid;

    public Rover(int x, int y, Direction direction, Grid grid) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.grid = grid;
    }

    public void move() {
    int newX = x + direction.getDx();
    int newY = y + direction.getDy();

    if (grid.isValidPosition(newX, newY) && !grid.hasObstacle(newX, newY)) {
        x = newX;
        y = newY;
    }
}

    public void turnLeft() {
        direction = direction.turnLeft();
    }

    public void turnRight() {
        direction = direction.turnRight();
    }

    public String getPosition() {
        return String.format("(%d, %d, %s)", x, y, direction);
    }

    public String getStatusReport() {
        return String.format("Rover is at (%d, %d) facing %s. %s",
                x, y, direction, grid.hasObstacle(x, y) ? "Obstacle detected." : "No obstacles detected.");
    }
}