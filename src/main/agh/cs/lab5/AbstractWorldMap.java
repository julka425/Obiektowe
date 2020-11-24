package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualiser;
import agh.cs.lab7.IPositionChangeObserver;

import java.util.*;


public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected LinkedList<Animal> animals;
    protected HashMap<Vector2d,Animal> animalHashMap = new HashMap<>();

    public AbstractWorldMap() {
        this.animals = new LinkedList<>();
    }

    public Animal getAnimal(int idx) {
        return animals.get(idx);
    }


    @Override
    public void positionChanged(Animal animal, Vector2d oldPosition, Vector2d newPosition) {
        animalHashMap.remove(oldPosition);
        animalHashMap.put(newPosition,animal);
    }


    @Override
    public abstract boolean canMoveTo(Vector2d position);


    @Override
    public boolean place(Animal animal) {
        if (!animalObjectAt(animal.getSituation()).isPresent()) {
            animals.add(animal);
            animalHashMap.put(animal.getSituation(),animal);
            return true;
        }
        return false;
    }



    @Override
    public void run(List<MoveDirection> directions) {
        int i=0;

        for (MoveDirection direction: directions) {
            Animal animal = animals.get(i%animals.size());
            animal.move(direction);
            i++;
        }
    }


    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position).isPresent();
    }


    public Optional<Object> animalObjectAt(Vector2d position) {
        if (animalHashMap.containsKey(position)) {
            return Optional.of(animalHashMap.get(position));
        }
        return Optional.empty();
    }


    @Override
    public abstract Optional<Object> objectAt(Vector2d position);


    public abstract Vector2d[] getExtremes();


    @Override
    public String toString() {
        MapVisualiser mapV = new MapVisualiser(this);
        Vector2d[] result = getExtremes();
        return mapV.draw(result[0], result[1]);
    }
}
