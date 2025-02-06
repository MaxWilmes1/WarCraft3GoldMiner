package org.example;

import java.util.ArrayList;
import java.util.List;

public class MiningRepresentation {

    public static List<String> generate(String path, int time) {
        List<String> simulation = new ArrayList<>();
        Worker w1 = new Worker();
        String currentPath = path;

        for (int t = 0; t < time; t++) {
            System.out.println("t is currently: "+ t);
            System.out.println("W1 Position: " + w1.getPosition());
            System.out.println("W1 direction: " + w1.getDirection());
            w1.move();
            currentPath = Map.calculateMap(currentPath, w1);
            simulation.add(currentPath);
            System.out.println("---------------");
        }

        return simulation;
    }

}
