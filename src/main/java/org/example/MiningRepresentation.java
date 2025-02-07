package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MiningRepresentation {

    public static List<String> generate(String path, int time) {
        List<String> simulationResult = new ArrayList<>();
        List<Worker> workers = new ArrayList<>();

        boolean initialPathContainsWorker;
        simulationResult.add(path);

        if ( path.contains("<") || path.contains(">") ){
            initialPathContainsWorker = true;
            workers = initializeWorker(path, workers);
        } else {
            initialPathContainsWorker = false;
        }

        for (int t = 1; t < time; t++) {
            System.out.println("time: "+t);
            if (initialPathContainsWorker){
                for (Worker w : workers) {
                    w.move(path);
                }
                path = generateNextSimulatedPath(path, workers);
            }
            simulationResult.add(path);
            System.out.println("--------");
        }
        return simulationResult;
    }

    public static List<Worker> initializeWorker(String path, List<Worker> workers){
        int pos;
        int index = 0;
        char[] pathArray = path.toCharArray();
        for (char c : pathArray) {
            if (c == '<' ) {
                pos = index;
                Worker w = new Worker(pos);
                w.setDirection("left");
                workers.add(w);
            }
            if (c == '>' ) {
                pos = index;
                Worker w = new Worker(pos);
                w.setDirection("right");
                workers.add(w);
            }
            index++;
        }
        return workers;
    }

    public static String generateNextSimulatedPath(String path, List<Worker> workers) {
        char[] pathArray = path.toCharArray();
        Set<Integer> seenPositions = new HashSet<>();
        Set<Integer> collisionPositions = new HashSet<>();

        // Setzt Basiswerte der Karte (ohne Arbeiter)
        pathArray[0] = 'M';
        pathArray[pathArray.length - 1] = 'B';
        for (int i = 1; i < pathArray.length - 1; i++) {
            pathArray[i] = '.';
        }

        // Kollisionen feststellen
        for (Worker w : workers) {
            int pos = w.getPosition();
            if (seenPositions.contains(pos)) {
                collisionPositions.add(pos);  // Mehrfach belegte Position = Kollision
            } else {
                seenPositions.add(pos);
            }
        }

        // Setzt Arbeiter auf die Karte (falls keine Kollision) + setze Kollisionen
        for (Worker w : workers) {
            int pos = w.getPosition();
            if (collisionPositions.contains(pos)) {
                pathArray[pos] = '#';  // Kollisionen markieren
            } else if (w.getDirection().equals("left")) {
                pathArray[pos] = '<';
            } else if (w.getDirection().equals("right")) {
                pathArray[pos] = '>';
            }
        }

        // Setzt Minen- oder Basisposition auf '*' falls von einem Arbeiter belegt
        for (int pos : seenPositions) {
            if (pos == 0 || pos == pathArray.length - 1) {
                pathArray[pos] = '*';
            }
        }

        return new String(pathArray);
    }


}
