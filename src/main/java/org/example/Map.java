package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Map {

    public static String calculateMap (String currentPath, Worker w1){
        String nextPath;
        System.out.println(currentPath);
        System.out.println("w1 position: " + w1.getPosition());
        System.out.println("w1 direction: " + w1.getDirection());
        char[] pathArray = currentPath.toCharArray();
        for (int i = 1; i < pathArray.length - 1; i++) {
            pathArray[i] = '.';
        }
        if (w1.getDirection().equals("left")){
            System.out.println("bin in left");
            pathArray[w1.getPosition()] = '<';
        }
        if (w1.getDirection().equals("right")){
            System.out.println("bin in right");
            pathArray[w1.getPosition()] = '>';
        }
        for (char c : pathArray) {
            System.out.println("char is: "+c);
        }
        nextPath = new String(pathArray);
        return nextPath;
    }

}
