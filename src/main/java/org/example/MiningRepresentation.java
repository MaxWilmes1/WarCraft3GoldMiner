package org.example;

import java.util.ArrayList;
import java.util.List;

public class MiningRepresentation {

    public static List<String> generate(String path, int time) {
        List<String> simulationResult = new ArrayList<>();
        Worker w = initializeWorker(path);
        boolean initialPathContainsWorker;
        simulationResult.add(path);

        if ( path.contains("<") || path.contains(">") ){
            initialPathContainsWorker = true;
        } else {
            initialPathContainsWorker = false;
        }

        for (int t = 1; t < time; t++) {
            if (initialPathContainsWorker){
                w.move(path);
                path = generateNextSimulatedPath(path, w);
            }
            simulationResult.add(path);
        }
        return simulationResult;
    }

    public static Worker initializeWorker(String path){
        Worker w = new Worker();
        if (path.contains("<") ) {
            int position1 = path.indexOf('<');
            w.setDirection("left");
            w.setPosition(position1);

        } else if ( path.contains(">")) {
            int position1 = path.indexOf('>');
            w.setDirection("right");
            w.setPosition(position1);
        }
        return w;
    }

    public static String generateNextSimulatedPath(String path, Worker w){
        String nextPath;
        char[] pathArray = path.toCharArray();
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
