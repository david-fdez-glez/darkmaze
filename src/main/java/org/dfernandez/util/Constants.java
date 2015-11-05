package org.dfernandez.util;

/**
 * Constants used by the App
 */
public final class Constants {

    public final static String ENSEMBLE_URL = "http://www.epdeveloperchallenge.com/api/";
    public final static String INIT_URI = "/init";
    public final static String MOVE_POSITION_URI = "/move";
    public final static String JUMP_POSITION_URI = "/jump";
    public final static String CURRENT_POSITION_URI = "/currentCell";
    public final static String MAZEGUID_PARAMETER = "mazeGuid";
    public final static String DIRECTION_PARAMETER = "direction";
    public final static String X_PARAMETER = "x";
    public final static String Y_PARAMETER = "y";
    public final static String APP_URL_ENCODER = "application/x-www-form-urlencoded";
    public final static int HEIGHT = 50;
    public final static int WIDTH = 50;

    private Constants() {
    }

}