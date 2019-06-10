package main;

import java.util.concurrent.ThreadLocalRandom;

public enum Direction {
    E(1, 0),
    NE(1, -1),
    N(0, -1),
    NW(-1, -1),
    W(-1, 0),
    SW(-1, 1),
    S(0, 1),
    SE(1, 1);

    public int dx, dy;

    private Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static Direction randomChoice() {
        Direction[] vs = values();
        return vs[ThreadLocalRandom.current().nextInt(vs.length)];
    }
}
