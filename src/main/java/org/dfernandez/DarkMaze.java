package org.dfernandez;

import org.dfernandez.algorithm.Bfs;
import org.dfernandez.algorithm.Dfs;
import org.dfernandez.algorithm.Search;
import org.dfernandez.exception.CommunicationException;
import org.dfernandez.exception.InternalException;
import org.dfernandez.exception.TooManyMazesException;
import org.dfernandez.model.Cell;
import org.dfernandez.rest.RestApiImpl;

import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;


public class DarkMaze {

    public static void main(String[] args) {
        try {

            System.out.println("Welcome to the Blackout Maze Challenge");
            System.out.println("Choose between the 2 algorithms. Any character will exit the program");
            System.out.println("1) Breadth-first search");
            System.out.println("2) Depth-first search");

            Scanner in = new Scanner(System.in);
            int entry;

            while (in.hasNextInt()) {
                entry = in.nextInt();

                DateFormat fmt = DateFormat.getDateTimeInstance();
                Date date = new Date();
                System.out.println("\nInitializating Dark Maze ..." + fmt.format(date));

                long iTime = System.currentTimeMillis();

                RestApiImpl restApi = new RestApiImpl();
                Cell cellStart = restApi.initMaze();

                Search searchAlg;

                switch (entry) {
                    case 1:
                        searchAlg = new Bfs(restApi, cellStart);
                        System.out.println("\nUsing Breadth-first search algorithm");
                        break;
                    case 2:
                        searchAlg = new Dfs(restApi, cellStart);
                        System.out.println("\nUsing Depth-first search algorithm");
                        break;
                    default:
                        searchAlg = new Bfs(restApi, cellStart);
                        System.out.println("\nUsing Breadth-first search algorithm");
                        break;
                }


                if (searchAlg.search()) {
                    System.out.println("Goal achieved.");
                } else {
                    System.out.println("There is no way out.");
                }


                long fTime = System.currentTimeMillis();

                long time = fTime - iTime;
                int seconds = (int) ((time / 1000) % 60);
                int minutes = (int) ((time / 1000) / 60);

                System.out.println(minutes + " minutes " + seconds + " seconds (miliseconds " + time + ")");

                System.out.println("\nChoose between the 2 algorithms. Any character will exit the program");
                System.out.println("1) Breadth-first search");
                System.out.println("2) Depth-first search");
            }

        } catch (CommunicationException | TooManyMazesException | InternalException e) {
            System.out.println(e.getMessage());
        }

    }
}