package org.dfernandez.algorithm;

/**
 * Structure with information about the location on the maze: X coordinate,
 * Y coordinate and distance "walked".
 */
public class Node {

    private int x;
    private int y;
    private int distance;


    public Node(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDistance() {
        return distance;
    }


}
