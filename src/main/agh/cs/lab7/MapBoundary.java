package agh.cs.lab7;

import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;


import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;



public class MapBoundary implements IPositionChangeObserver {

    private LinkedList<Animal> sortedX = new LinkedList<>();
    private LinkedList<Animal> sortedY = new LinkedList<>();

    public LinkedList<Animal> getSortedX() {
        return sortedX;
    }

    public LinkedList<Animal> getSortedY() {
        return sortedY;
    }



    public int compareHelper (int x1, int x2, int y1, int y2) {
        if (x1 - x2 == 0) {
            return y1 - y2;
        }
        else return x1 - x2;
    }

    class sortByX implements Comparator<Animal> {
        @Override
        public int compare(Animal o1, Animal o2) {
            return compareHelper(o1.getSituation().x, o2.getSituation().x, o1.getSituation().y, o2.getSituation().y);
        }
    }


    class sortByY implements Comparator<Animal> {
        @Override
        public int compare(Animal o1, Animal o2) {
            return compareHelper(o1.getSituation().y,o2.getSituation().y,o1.getSituation().x,o2.getSituation().x);
        }
    }

    public void sorting(LinkedList<Animal> sortX, LinkedList<Animal> sortY) {
        Collections.sort(sortedX, new sortByX());
        Collections.sort(sortedY, new sortByY());
    }


    public void upgrade(Animal animal) {
        sortedX.add(animal);
        sortedY.add(animal);
        sorting(sortedX,sortedY);
    }




    public void positionChangedHelper(LinkedList<Animal> list, Animal animal, int x1, int x2, int y1, int y2, boolean first) {
        if (first && (x1 - x2 < 0) || (x1 - x2 == 0 && y1 - y2 < 0)) {
            list.addFirst(animal);
        }
        else if (!first && (x1 - x2 > 0) || (x1 - x2 == 0 && y1 - y2 > 0)) {
            list.addLast(animal);
        }
    }

    @Override
    public void positionChanged(Animal animal, Vector2d oldPosition, Vector2d newPosition) {
        Vector2d firstX = sortedX.getFirst().getSituation();
        Vector2d lastX = sortedX.getLast().getSituation();
        Vector2d firstY = sortedX.getFirst().getSituation();
        Vector2d lastY = sortedX.getLast().getSituation();

        positionChangedHelper(sortedX, animal, newPosition.x, firstX.x, newPosition.y, firstX.y,true);
        positionChangedHelper(sortedY, animal, newPosition.y, firstY.y, newPosition.x, firstY.x,true);
        positionChangedHelper(sortedX, animal, newPosition.x,lastX.x,newPosition.y,lastX.y,false);
        positionChangedHelper(sortedY, animal, newPosition.y, lastY.y, newPosition.x, lastY.x, false);

    }
}
