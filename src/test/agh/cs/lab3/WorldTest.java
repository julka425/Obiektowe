package agh.cs.lab3;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.MapDirection;
import agh.cs.lab2.Vector2d;

import agh.cs.lab4.RectangularMap;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WorldTest {
    RectangularMap map = new RectangularMap(5,5);

    @Test
    void shouldBeProperlyOrientedAfterTurningLeft() {
        Animal animal = new Animal(map);
        assertEquals(MapDirection.NORTH, animal.getDirection());

        animal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.WEST, animal.getDirection());

        animal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.SOUTH, animal.getDirection());

        animal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.EAST, animal.getDirection());

        animal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.NORTH, animal.getDirection());
    }

    @Test
    void shouldBeProperlyOrientedAfterTurningRight() {
        Animal animal = new Animal(map);

        animal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.EAST, animal.getDirection());

        animal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.SOUTH, animal.getDirection());

        animal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.WEST, animal.getDirection());

        animal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.NORTH, animal.getDirection());
    }


    @Test
    void shouldChangePositionsProperlyGoingForward() {
        Animal animal = new Animal(map);

        animal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d (2,3), animal.getSituation());

        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(1,3), animal.getSituation());

        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(1,2), animal.getSituation());

    }

    @Test
    void shouldChangePositionsProperlyGoingBackward() {
        Animal animal = new Animal(map);

        animal.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d (2,1), animal.getSituation());

        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(3,1), animal.getSituation());

        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(3,2), animal.getSituation());

    }

    @Test
    void shouldStayInPlaceWhenAtUpperBorder() {
        Animal animal = new Animal(map);
        for (int i=0; i<2 ; i++) {
            animal.move(MoveDirection.FORWARD);
        }
        Vector2d v = new Vector2d(2,4);
        assertEquals(v, animal.getSituation());
        animal.move(MoveDirection.FORWARD);
        assertEquals(v,animal.getSituation());

    }

    @Test
    void shouldStayInPlaceWhenAtRightBorder() {
        Animal animal = new Animal(map);
        animal.move(MoveDirection.RIGHT);

        for (int i=0; i<2; i++) {
            animal.move(MoveDirection.FORWARD);
        }

        Vector2d v = new Vector2d(4,2);
        assertEquals(v, animal.getSituation());
        animal.move(MoveDirection.FORWARD);
        assertEquals(v,animal.getSituation());
    }

    @Test
    void shouldStayInPlaceWhenAtLowerBorder() {
        Animal animal = new Animal(map);
        for (int i=0; i<2 ; i++) {
            animal.move(MoveDirection.BACKWARD);
        }
        Vector2d v = new Vector2d(2,0);
        assertEquals(v, animal.getSituation());
        animal.move(MoveDirection.BACKWARD);
        assertEquals(v,animal.getSituation());
    }


    @Test
    void shouldStayInPlaceWhenAtLeftBorder() {
        Animal animal = new Animal(map);
        animal.move(MoveDirection.RIGHT);
        for (int i=0; i<2 ; i++) {
            animal.move(MoveDirection.BACKWARD);
        }
        Vector2d v = new Vector2d(0,2);
        assertEquals(v, animal.getSituation());
        animal.move(MoveDirection.BACKWARD);
        assertEquals(v,animal.getSituation());
    }



    @Test
    void shouldConvertToList() {
        //given
        String[] arr = {"f", "l", "forward", "left", "r", "right", "b", "backward", "l"};
        //when
        LinkedList<MoveDirection> list = OptionParser.parse(arr);
        //then
        LinkedList <MoveDirection> expected = new LinkedList<MoveDirection>();

        expected.add(MoveDirection.FORWARD);
        expected.add(MoveDirection.LEFT);
        expected.add(MoveDirection.FORWARD);
        expected.add(MoveDirection.LEFT);
        expected.add(MoveDirection.RIGHT);
        expected.add(MoveDirection.RIGHT);
        expected.add(MoveDirection.BACKWARD);
        expected.add(MoveDirection.BACKWARD);
        expected.add(MoveDirection.LEFT);

        assertEquals(expected, list);
    }

}