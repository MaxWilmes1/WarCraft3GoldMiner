package org.example;

import java.util.ArrayList;
import java.util.List;

public class MiningRepresentation {

    public static List<String> generate(String path, int time) {
        List<String> simulationResult = new ArrayList<>();
        Worker w = initializeGame(path);
        String currentPath = path;
        boolean initialPathContainsWorker;

        if ( currentPath.contains("<") || currentPath.contains(">") ){
            initialPathContainsWorker = true;
        } else {
            initialPathContainsWorker = false;
        }

        for (int t = 0; t < time; t++) {
            if (initialPathContainsWorker){
                currentPath = generateNextSimulatedPath(currentPath, w);
                w.move(currentPath);
            }
            simulationResult.add(currentPath);
        }
        return simulationResult;
    }

    public static Worker initializeGame(String initalPath){
        Worker w = new Worker();
        if (initalPath.contains("<") ) {
            int position1 = initalPath.indexOf('<');
            w.setMine(false);
            w.setBase(false);
            w.setDirection("left");
            w.setPosition(position1);

        } else if ( initalPath.contains(">")) {
            int position1 = initalPath.indexOf('>');
            w.setMine(false);
            w.setBase(false);
            w.setDirection("right");
            w.setPosition(position1);
        }
        return w;
    }

    public static String generateNextSimulatedPath(String currentPath, Worker w){
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
}
