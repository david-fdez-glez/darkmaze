package org.dfernandez.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Each location of the Maze
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "currentCell")
public class Cell {


    private String note;
    private String mazeGuid;
    private boolean atEnd;
    private RouteType north;
    private RouteType east;
    private RouteType south;
    private RouteType west;
    private int x;
    private int y;
    private boolean previouslyVisited;


    public Cell(String note, String mazeGuid, boolean atEnd, RouteType north, RouteType east, RouteType south, RouteType west, int x, int y, boolean previouslyVisited) {
        this.note = note;
        this.mazeGuid = mazeGuid;
        this.atEnd = atEnd;
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
        this.x = x;
        this.y = y;
        this.previouslyVisited = previouslyVisited;
    }

    @XmlElement
    public String getNote() {
        return note;
    }

    @XmlElement
    public String getMazeGuid() {
        return mazeGuid;
    }

    @XmlElement
    public boolean isAtEnd() {
        return atEnd;
    }

    @XmlElement
    public RouteType getNorth() {
        return north;
    }

    @XmlElement
    public RouteType getEast() {
        return east;
    }

    @XmlElement
    public RouteType getSouth() {
        return south;
    }

    @XmlElement
    public RouteType getWest() {
        return west;
    }

    @XmlElement
    public int getX() {
        return x;
    }

    @XmlElement
    public int getY() {
        return y;
    }

    @XmlElement
    public boolean isPreviouslyVisited() {
        return previouslyVisited;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "\"" + "note" + "\"" + " =" + "\"" + note + "\"" +
                "," + "\"" + "mazeGuid" + "\"" + "=" + "\"" + mazeGuid + "\"" +
                ", x=" + x +
                ", y=" + y +
                ", previouslyVisited=" + previouslyVisited +
                ", atEnd=" + atEnd +
                ", north=" + north +
                ", east=" + east +
                ", south=" + south +
                ", west=" + west +
                '}';
    }
}