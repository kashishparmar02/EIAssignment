package rover;

public enum Direction {
    NORTH(0, 1) {
        @Override
        public Direction turnLeft() { return WEST; }
        @Override
        public Direction turnRight() { return EAST; }
    },
    EAST(1, 0) {
        @Override
        public Direction turnLeft() { return NORTH; }
        @Override
        public Direction turnRight() { return SOUTH; }
    },
    SOUTH(0, -1) {
        @Override
        public Direction turnLeft() { return EAST; }
        @Override
        public Direction turnRight() { return WEST; }
    },
    WEST(-1, 0) {
        @Override
        public Direction turnLeft() { return SOUTH; }
        @Override
        public Direction turnRight() { return NORTH; }
    };

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() { return dx; }
    public int getDy() { return dy; }

    public abstract Direction turnLeft();
    public abstract Direction turnRight();
}
