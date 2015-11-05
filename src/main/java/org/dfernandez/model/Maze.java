package org.dfernandez.model;


/**
 * Dark Maze
 */
public class Maze {

    private String mazeGuid;
    private Cell[][] matrix;


    public Maze(String mazeGuid, int height, int width) {
        this.mazeGuid = mazeGuid;
        this.matrix = new Cell[height][width];
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                this.matrix[x][y] = new Cell("", this.mazeGuid, false, null, null, null, null, x, y, false);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return matrix[x][y];
    }

    public void setCell(int x, int y, Cell cell) {
        this.matrix[x][y] = cell;
    }

    public String getMazeGuid() {
        return mazeGuid;
    }


}