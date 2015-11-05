package org.dfernandez.rest;


import org.dfernandez.exception.CommunicationException;
import org.dfernandez.exception.InternalException;
import org.dfernandez.exception.TooManyMazesException;
import org.dfernandez.model.Cell;
import org.dfernandez.model.Direction;
import org.dfernandez.model.Maze;
import org.dfernandez.util.Constants;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientRequestFactory;
import org.jboss.resteasy.client.ClientResponse;

import javax.ws.rs.core.UriBuilder;

/**
 * Implementation of Blackout API, using RestEasy
 */
public class RestApiImpl implements RestApi {


    private Maze actualMaze;
    private ClientRequestFactory requestFactory;

    public RestApiImpl() {

        requestFactory = new ClientRequestFactory(UriBuilder.fromUri(Constants.ENSEMBLE_URL).build());
    }

    public Maze getActualMaze() {
        return actualMaze;
    }

    public void setActualMaze(Maze actualMaze) {
        this.actualMaze = actualMaze;
    }

    /**
     * Validate http response status
     *
     * @param response Response extension for the RESTEasy client framework.
     * @throws CommunicationException
     */
    private void validateResponse(ClientResponse<?> response) throws CommunicationException {

        if (response.getStatus() != 200 && response.getStatus() != 400) {
            throw new CommunicationException("The response from the server is not the expected one. The program will stop now. Try it later.");
        }
    }

    public Cell initMaze() throws CommunicationException, TooManyMazesException, InternalException {

        ClientRequest initRequest = requestFactory.createRelativeRequest(Constants.INIT_URI);

        ClientResponse<Cell> response;
        try {
            response = initRequest.post(Cell.class);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }

        validateResponse(response);
        if (response.getStatus() == 400) {
            throw new TooManyMazesException("Too many mazes currently in memory on the server. Please wait a few minutes and try again ");
        }

        Cell currentCell = response.getEntity();

        setActualMaze(new Maze(currentCell.getMazeGuid(), Constants.HEIGHT, Constants.WIDTH));

        getActualMaze().setCell(currentCell.getX(), currentCell.getY(), currentCell);

        initRequest.clear();
        response.releaseConnection();

        return currentCell;
    }

    public Cell getActualInfo() throws InternalException {

        ClientRequest initRequest = requestFactory.createRelativeRequest(Constants.CURRENT_POSITION_URI);

        initRequest.queryParameter(Constants.MAZEGUID_PARAMETER, this.actualMaze.getMazeGuid());

        ClientResponse<Cell> response;

        try {
            response = initRequest.get(Cell.class);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }

        Cell cell = response.getEntity();
        initRequest.clear();
        response.releaseConnection();

        return cell;
    }

    public Cell movePosition(Direction direction) throws InternalException {

        ClientRequest initRequest = requestFactory.createRelativeRequest(Constants.MOVE_POSITION_URI);

        Cell currentCell = null;
        initRequest.queryParameter(Constants.MAZEGUID_PARAMETER, this.actualMaze.getMazeGuid());
        initRequest.queryParameter(Constants.DIRECTION_PARAMETER, direction);
        initRequest.accept(Constants.APP_URL_ENCODER);

        ClientResponse<Cell> response;
        try {
            response = initRequest.post(Cell.class);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }

        if (response.getStatus() == 400) {
            System.out.println("You are attempting to move in a direction that is not allowed due to a wall Direction " + direction);
        } else {
            currentCell = response.getEntity();
            getActualMaze().setCell(currentCell.getX(), currentCell.getY(), currentCell);
        }


        initRequest.clear();
        response.releaseConnection();

        return currentCell;
    }

    public void jumpPosition(int x, int y) throws InternalException {

        ClientRequest initRequest = requestFactory.createRelativeRequest(Constants.JUMP_POSITION_URI);

        initRequest.queryParameter(Constants.MAZEGUID_PARAMETER, this.actualMaze.getMazeGuid());
        initRequest.queryParameter(Constants.X_PARAMETER, x);
        initRequest.queryParameter(Constants.Y_PARAMETER, y);
        initRequest.accept(Constants.APP_URL_ENCODER);

        ClientResponse<Cell> response;
        try {
            response = initRequest.post(Cell.class);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }

        if (response.getStatus() == 400) {
            System.out.println("You are attempting to jump to a location that you have not previously visited");
        }


        initRequest.clear();
        response.releaseConnection();
    }

}