package org.dfernandez.algorithm;

import org.dfernandez.exception.InternalException;
import org.dfernandez.model.Cell;
import org.dfernandez.model.Direction;
import org.dfernandez.model.Maze;
import org.dfernandez.model.RouteType;
import org.dfernandez.rest.RestApi;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test Maze[2][2] without solution and Maze[2][2] with solution
 */
public class BfsTest {


    private final String mazeGuid = "d172045e-3593-4122-bb61-3d7144b9e615";
    private Mockery mockery = new JUnit4Mockery();

    @Test
    public void mazeWithoutSolution() throws InternalException {

        final RestApi restApi = mockery.mock(RestApi.class);

        Cell startCell = new Cell("(0,0)", mazeGuid, false, RouteType.BLOCKED, RouteType.BLOCKED, RouteType.UNEXPLORED, RouteType.BLOCKED, 0, 0, false);
        final Cell southCell = new Cell("(0,1)", mazeGuid, false, RouteType.VISITED, RouteType.UNEXPLORED, RouteType.BLOCKED, RouteType.BLOCKED, 0, 1, false);
        Cell eastCell = new Cell("(1,0)", mazeGuid, false, RouteType.BLOCKED, RouteType.BLOCKED, RouteType.BLOCKED, RouteType.VISITED, 1, 0, false);
        final Cell lastCell = new Cell("(1,1)", mazeGuid, false, RouteType.BLOCKED, RouteType.BLOCKED, RouteType.BLOCKED, RouteType.VISITED, 1, 1, false);
        final Maze blockedMaze = new Maze(mazeGuid, 2, 2);

        blockedMaze.setCell(0, 0, startCell);
        blockedMaze.setCell(0, 1, southCell);
        blockedMaze.setCell(1, 0, eastCell);
        blockedMaze.setCell(1, 1, lastCell);

        Bfs bfs = new Bfs(restApi, startCell);

        mockery.checking(new Expectations() {{
            atLeast(1).of(restApi).jumpPosition(with(any(Integer.class)), with(any(Integer.class)));
            atLeast(1).of(restApi).getActualMaze();
            will(returnValue(blockedMaze));
            oneOf(restApi).movePosition(with(any(Direction.class)));
            will(returnValue(southCell));
            oneOf(restApi).movePosition(with(any(Direction.class)));
            will(returnValue(lastCell));
        }});

        assertFalse("You are out of an impossible maze", bfs.search());

        mockery.assertIsSatisfied();
    }

    @Test
    public void mazeWithSolution() throws InternalException {

        final RestApi restApi = mockery.mock(RestApi.class);

        Cell startCell = new Cell("", mazeGuid, false, RouteType.BLOCKED, RouteType.BLOCKED, RouteType.UNEXPLORED, RouteType.BLOCKED, 0, 0, false);
        final Cell southCell = new Cell("", mazeGuid, false, RouteType.VISITED, RouteType.UNEXPLORED, RouteType.BLOCKED, RouteType.BLOCKED, 0, 1, false);
        Cell eastCell = new Cell("", mazeGuid, false, RouteType.BLOCKED, RouteType.BLOCKED, RouteType.UNEXPLORED, RouteType.VISITED, 1, 0, false);
        final Cell lastCell = new Cell("", mazeGuid, true, RouteType.BLOCKED, RouteType.BLOCKED, RouteType.BLOCKED, RouteType.VISITED, 1, 1, false);
        final Maze blockedMaze = new Maze(mazeGuid, 2, 2);

        blockedMaze.setCell(0, 0, startCell);
        blockedMaze.setCell(0, 1, southCell);
        blockedMaze.setCell(1, 0, eastCell);
        blockedMaze.setCell(1, 1, lastCell);

        Bfs bfs = new Bfs(restApi, startCell);

        mockery.checking(new Expectations() {{
            atLeast(1).of(restApi).jumpPosition(with(any(Integer.class)), with(any(Integer.class)));
            atLeast(1).of(restApi).getActualMaze();
            will(returnValue(blockedMaze));
            oneOf(restApi).movePosition(with(any(Direction.class)));
            will(returnValue(southCell));
            oneOf(restApi).movePosition(with(any(Direction.class)));
            will(returnValue(lastCell));
        }});


        assertTrue("You should be out of the maze and you are not", bfs.search());

        mockery.assertIsSatisfied();
    }


}
