package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Map {

    public static String calculateNextPath (String currentPath, Worker w){
        String nextPath;
        char[] pathArray = currentPath.toCharArray();
        for (int i = 1; i < pathArray.length - 1; i++) {
            pathArray[i] = '.';
        }
        if (w.getDirection().equals("left")){
            pathArray[w.getPosition()] = '<';
        }
        if (w.getDirection().equals("right")){
            pathArray[w.getPosition()] = '>';
        }
        if (w.getPosition() == 0 || w.getPosition() == pathArray.length - 1) {
            pathArray[w.getPosition()] = '*';
        } else {
            pathArray[0] = 'M';
            pathArray[pathArray.length - 1] = 'B';
        }
        nextPath = new String(pathArray);
        return nextPath;
    }

    public static void initializeGame(String initalPath) {
    }
}
