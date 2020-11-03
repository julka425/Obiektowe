package agh.cs.lab4;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;

import java.util.*;


public class RectangularMap implements IWorldMap {
    private int width;
    private int height;
    private ArrayList<Animal> animals;

    final int LOWER = 0;

    public RectangularMap(int width, int height) {
        this.height = height;
        this.width = width;
        this.animals = new ArrayList<>();
    }



    public ArrayList<Animal> getAnimals() {
        return animals;
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.x >= LOWER && position.x < width &&
                position.y >= LOWER && position.y < height)
            return (!isOccupied(position));
        return false;
    }


    @Override
    public boolean place(Animal animal) {
        if (!isOccupied(animal.getSituation())) {
            animals.add(animal);
            return true;
        }
        return false;
    }


    @Override
    public void run(List<MoveDirection> directions) {
        int i=0;

        for (MoveDirection direction: directions) {
            animals.get(i).move(direction);
            i++;
            if (i>=animals.size()) i=0;
        }
    }


    @Override
    public boolean isOccupied(Vector2d position) {

        for (Animal animal: animals) {
            if (animal.getSituation().equals(position))
                return true;
        }
        return false;
    }


    @Override
    public Optional<Object> objectAt(Vector2d position) {
        for (Animal animal: animals) {
            if (animal.getSituation().equals(position))
                return Optional.of(animal);
        }
        return Optional.empty();
    }


    @Override
    public String toString() {
        MapVisualiser mapV = new MapVisualiser(this);
        return mapV.draw(new Vector2d(LOWER,LOWER), new Vector2d(width-1, height -1));
    }
}
