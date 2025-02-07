package org.example;

import java.util.ArrayList;
import java.util.List;

public class MiningRepresentation {

    public static List<String> generate(String path, int time) {
        String currentPath = path;
        List<String> simulation = new ArrayList<>();
        Worker w = initializeGame(path);

        for (int t = 0; t < time; t++) {
            if (w != null){
                currentPath = Map.calculateNextPath(currentPath, w);
                w.move(currentPath);
            }
            simulation.add(currentPath);
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
