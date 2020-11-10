package agh.cs.lab3;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.RectangularMap;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    RectangularMap map = new RectangularMap(5,5);

    @Test
    void shouldTurnRight() {
        //given
        Animal animal = new Animal(map);
        //when
        animal.move(MoveDirection.RIGHT);
        //then
        assertEquals(MapDirection.EAST, animal.getDirection());
    }

    @Test
    void shouldMoveForward() {
        //given
        Animal animal = new Animal(map);
        animal.move(MoveDirection.RIGHT);
        //when
        animal.move(MoveDirection.FORWARD);
        //then
        assertEquals(new Vector2d(3,2), animal.getSituation());
    }

    @Test
    void shouldMoveBackward() {
        //given
        Animal animal = new Animal(map);
        //when
        animal.move(MoveDirection.BACKWARD);
        //then
        assertEquals(new Vector2d(2,1), animal.getSituation());
    }

    @Test
    void shouldTurnLeft() {
        //given
        Animal animal = new Animal(map);
        //when
        animal.move(MoveDirection.LEFT);
        //then
        assertEquals(MapDirection.WEST, animal.getDirection());
    }

    @Test
    void shouldStayInPlace() {
        //given
        Animal animal = new Animal(map);
        animal.move(MoveDirection.LEFT);
        //when
        for (int i=0; i<3; i++) {
            animal.move(MoveDirection.FORWARD);
        }
        //then
        assertEquals(new Vector2d(0,2), animal.getSituation());
    }

}