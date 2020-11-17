package agh.cs.lab5;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualiser;

import java.util.*;


public abstract class AbstractWorldMap implements IWorldMap {
    protected LinkedList<Animal> animals;
    protected HashMap<Vector2d,Animal> animalHashMap = new HashMap<>();

    public AbstractWorldMap() {
        this.animals = new LinkedList<>();
    }


    public Vector2d getAnimalSituation(int idx) {
        return animals.get(idx).getSituation();
    }

    public MapDirection getAnimalDirection(int idx) {
        return animals.get(idx).getDirection();
    }

    public Integer getNumberOfAnimals() {
        return animals.size();
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
            Vector2d position = animal.getSituation();
            animal.move(direction);

            if (animal.getSituation()!=position) {
                animalHashMap.remove(position);
                animalHashMap.put(animal.getSituation(),animal);
            }
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
