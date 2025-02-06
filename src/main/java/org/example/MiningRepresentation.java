package org.example;

import java.util.ArrayList;
import java.util.List;

public class MiningRepresentation {

    public static List<String> generate(String path, int time) {
        String currentPath = path;
        List<String> simulation = new ArrayList<>();
        Worker w = initializeGame(path); //initializiere Game, erstelle Worker mit passenden Parametern

        for (int t = 0; t < time; t++) {
            System.out.println("t is currently: "+ t);
            if (w != null){
                System.out.println("W1 Position: " + w.getPosition());
                System.out.println("W1 direction: " + w.getDirection());
                w.move();
                currentPath = Map.calculateNextPath(currentPath, w);
            }
            simulation.add(currentPath);
            System.out.println("---------------");
        }

        return simulation;
    }

    public static Worker initializeGame(String initalPath){
        Worker w1 = null;
        if (initalPath.contains("<") ) {
            int position1 = initalPath.indexOf('<');
            w1 = new Worker();
            w1.setMine(false);
            w1.setBase(false);
            w1.setDirection("left");
            w1.setPosition(position1);

        } else if ( initalPath.contains(">")) {
            int position1 = initalPath.indexOf('>');
            w1 = new Worker();
            w1.setMine(false);
            w1.setBase(false);
            w1.setDirection("right");
            w1.setPosition(position1);
        }
        return w1;
    }
}
