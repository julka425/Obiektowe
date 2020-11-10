package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab3.OptionParser;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GrassfieldTest {

    Grassfield map = new Grassfield(10);

    @Test
    void shouldReturnIfAnimalCouldMoveToSaidPosition() {
        Animal animal = new Animal(map);
        assertEquals(false, map.canMoveTo(new Vector2d(2,2)));
        assertEquals(true, map.canMoveTo(new Vector2d(2,3)));
    }



    @Test
    void shouldReturnIfPlacedAnAnimal() {
        //should place
        assertEquals(true, map.place(new Animal()));
        //shouldn't place
        assertEquals(false, map.place(new Animal()));
    }



    @Test
    void shouldChangePositionsCorrectly() {
        String[] array = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        LinkedList<MoveDirection> directions = new OptionParser().parse(array);
        map.place(new Animal((map)));
        map.place(new Animal(map,new Vector2d(3,4)));
        map.run(directions);
        assertEquals(new Vector2d(2,-1), map.getAnimals().get(0).getSituation());
        assertEquals(new Vector2d(3,7), map.getAnimals().get(1).getSituation());

    }



    @Test
    void shouldReturnIfPositionIsOccupied() {
        //animal
        Animal animal = new Animal(map);
        assertEquals(true, map.isOccupied(animal.getSituation()));
        //grass
        assertEquals(true, map.isOccupied(map.getGrassList().get(0).getPosition()));
        //nothing
        assertEquals(false, map.isOccupied(new Vector2d(-3,-12)));
    }



    @Test
    void shouldCorrectlyReturnObjectAtThePosition() {
        //animal
        Animal animal = new Animal(map);
        assertEquals(Optional.of(animal), map.objectAt(animal.getSituation()));
        //grass
        Grass grass = map.getGrassList().get(0);
        assertEquals(Optional.of(grass), map.objectAt(grass.getPosition()));
        //nothing
        assertEquals(Optional.empty(), map.objectAt(new Vector2d(-3,-12)));
    }


}