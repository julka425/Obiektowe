package agh.cs.lab4;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab3.OptionParser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    String[] array = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
    LinkedList<MoveDirection> directions = new OptionParser().parse(array);
    RectangularMap map = new RectangularMap(10,5);


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
        map.place(new Animal((map)));
        map.place(new Animal(map,new Vector2d(3,4)));
        map.run(directions);
        assertEquals(new Vector2d(2,0), map.getAnimal(0).getSituation());
        assertEquals(new Vector2d(3,4), map.getAnimal(1).getSituation());

    }



    @Test
    void shouldReturnIfPositionIsOccupied() {
        //animal
        Animal animal = new Animal(map);
        assertEquals(true, map.isOccupied(animal.getSituation()));
        //nothing
        assertEquals(false, map.isOccupied(new Vector2d(3,3)));
    }



    @Test
    void shouldCorrectlyReturnObjectAtThePosition() {
        //animal
        Animal animal = new Animal(map);
        assertEquals(Optional.of(animal), map.objectAt(animal.getSituation()));
        //nothing
        assertEquals(Optional.empty(), map.objectAt(new Vector2d(3,3)));
    }



    @Test
    void integral() {
        RectangularMap map = new RectangularMap(10, 5);
        map.place(new Animal((map)));
        map.place(new Animal(map,new Vector2d(3,4)));

        LinkedList<MoveDirection> directions = OptionParser.parse(Arrays.copyOfRange(array, 0, 2));
        map.run(directions);
        assertEquals(new Vector2d(2,3),map.getAnimal(0).getSituation());
        assertEquals(new Vector2d(3,3),map.getAnimal(1).getSituation());

        directions = OptionParser.parse(Arrays.copyOfRange(array, 2, 4));
        map.run(directions);
        assertEquals(MapDirection.EAST, map.getAnimal(0).getDirection());
        assertEquals(MapDirection.WEST,map.getAnimal(1).getDirection());

        directions = OptionParser.parse(Arrays.copyOfRange(array, 4, 6));
        map.run(directions);
        assertEquals(new Vector2d(2,3),map.getAnimal(0).getSituation());
        assertEquals(new Vector2d(3,3),map.getAnimal(1).getSituation());

        directions = OptionParser.parse(Arrays.copyOfRange(array, 6, 8));
        map.run(directions);
        assertEquals(MapDirection.SOUTH,map.getAnimal(0).getDirection());
        assertEquals(MapDirection.NORTH,map.getAnimal(1).getDirection());

        directions = OptionParser.parse(Arrays.copyOfRange(array, 8, 10));
        map.run(directions);
        assertEquals(new Vector2d(2,2),map.getAnimal(0).getSituation());
        assertEquals(new Vector2d(3,4),map.getAnimal(1).getSituation());

        directions = OptionParser.parse(Arrays.copyOfRange(array, 10, 12));
        map.run(directions);
        assertEquals(new Vector2d(2,1),map.getAnimal(0).getSituation());
        assertEquals(new Vector2d(3,4),map.getAnimal(1).getSituation());

        directions = OptionParser.parse(Arrays.copyOfRange(array, 12, 14));
        map.run(directions);
        assertEquals(new Vector2d(2,0),map.getAnimal(0).getSituation());
        assertEquals(new Vector2d(3,4),map.getAnimal(1).getSituation());

        directions = OptionParser.parse(Arrays.copyOfRange(array, 14, 16));
        map.run(directions);
        assertEquals(new Vector2d(2,0),map.getAnimal(0).getSituation());
        assertEquals(new Vector2d(3,4),map.getAnimal(1).getSituation());
    }
}