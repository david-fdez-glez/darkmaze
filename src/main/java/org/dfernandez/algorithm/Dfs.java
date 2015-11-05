package org.dfernandez.algorithm;

import org.dfernandez.exception.InternalException;
import org.dfernandez.model.Cell;
import org.dfernandez.rest.RestApiImpl;

import java.util.Stack;

/**
 * Depth-first search algorithm
 */
public class Dfs extends AbstractSearch {


    private Stack<Node> stack;


    public Dfs(RestApiImpl restApi, Cell cellStart) {
        super(restApi, cellStart);
        this.stack = new Stack<>();
    }

    /**
     * Implementation of the Algorithm Depth-first search
     *
     * @return
     * @throws InternalException
     */
    public boolean search() throws InternalException {

        Node actual;
        int x, y;

        stack.push(startNode);

        while (stack.peek() != null) {

            actual = stack.pop();
            x = actual.getX();
            y = actual.getY();
            restApi.jumpPosition(x, y);

            if (isSolution(restApi.getActualMaze().getCell(x, y))) {

                printSolution(x, y, actual);
                return true;
            }

            expandNeighbours(x, y, actual);

        }

        return false;
    }

    /**
     * Add a Node to the stack (data structure to store intermediate results)
     *
     * @param node Node(int x, int y, int distance)
     */
    @Override
    protected void addNode(Node node) {
        stack.push(node);
    }


}