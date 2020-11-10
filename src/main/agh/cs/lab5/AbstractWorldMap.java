package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualiser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



public class AbstractWorldMap implements IWorldMap {
    private ArrayList<Animal> animals;

    public AbstractWorldMap() {
        this.animals = new ArrayList<>();
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return false;
    }


    @Override
    public boolean place(Animal animal) {
        if (!animalObjectAt(animal.getSituation()).isPresent()) {
            animals.add(animal);
            return true;
        }
        return false;
    }


    @Override
    public void run(List<MoveDirection> directions) {
        int i=0;

        for (MoveDirection direction: directions) {
            animals.get(i%animals.size()).move(direction);
            i++;
        }
    }


    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position).isPresent();
    }


    public Optional<Object> animalObjectAt(Vector2d position) {
        for (Animal animal: animals) {
            if (animal.getSituation().equals(position))
                return Optional.of(animal);
        }
        return Optional.empty();
    }


    @Override
    public Optional<Object> objectAt(Vector2d position) {
        return Optional.empty();
    }


    public Vector2d[] getExtremes() {
        Vector2d[] result = new Vector2d[2];
        result[0] = new Vector2d(0,0);
        result[1] = new Vector2d(0,0);
        return result;
    }


    @Override
    public String toString() {
        MapVisualiser mapV = new MapVisualiser(this);
        Vector2d[] result = getExtremes();
        return mapV.draw(result[0], result[1]);
    }
}
