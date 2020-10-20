package agh.cs.lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void shouldReturnNewDirection() {

        //first case - next after north
        //given
        MapDirection direction = null;
        //when
        MapDirection result = direction.NORTH.next();
        //then
        assertEquals(direction.EAST, result);


        //second case - next after east
        //when
        MapDirection result2 = direction.EAST.next();
        //then
        assertEquals(direction.SOUTH, result2);


        //third case - next after south
        //when
        MapDirection result3 = direction.SOUTH.next();
        //then
        assertEquals(direction.WEST, result3);



        //fourth case - next after west
        //when
        MapDirection result4 = direction.WEST.next();
        //then
        assertEquals(direction.NORTH, result4);
    }





    @Test
    void shouldReturnPreviousDirection() {

        //first case - previous to north
        //given
        MapDirection direction = null;
        //when
        MapDirection result = direction.NORTH.previous();
        //then
        assertEquals(direction.WEST, result);


        //second case - previous to east
        //when
        MapDirection result2 = direction.EAST.previous();
        //then
        assertEquals(direction.NORTH, result2);


        //third case - previous to south
        //when
        MapDirection result3 = direction.SOUTH.previous();
        //then
        assertEquals(direction.EAST, result3);



        //fourth case - previous to west
        //when
        MapDirection result4 = direction.WEST.previous();
        //then
        assertEquals(direction.SOUTH, result4);
    }
}