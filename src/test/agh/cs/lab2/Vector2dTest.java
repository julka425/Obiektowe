package agh.cs.lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void shouldCheckIfPrecedes() {
        //given
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(-2,1);
        //when
        boolean isPrevious = v2.precedes(v1);
        //then
        assertEquals(true, isPrevious);
    }



    @Test
    void shouldCheckIfFollows() {
        //given
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(-2,1);
        //when
        boolean isNext = v2.follows(v1);
        //then
        assertEquals(false, isNext);
    }



    @Test
    void shouldReturnUpperRightCorner() {
        //given
        Vector2d v1 = new Vector2d(2,1);
        Vector2d v2 = new Vector2d(1,2);
        //when
        Vector2d uppR = v1.upperRight(v2);
        //then
        assertEquals(new Vector2d(2,2), uppR);
    }



    @Test
    void shouldReturnLowerLeftCorner() {
        //given
        Vector2d v1 = new Vector2d(2,1);
        Vector2d v2 = new Vector2d(1,2);
        //when
        Vector2d lowL = v1.lowerLeft(v2);
        //then
        assertEquals(new Vector2d(1,1), lowL);
    }



    @Test
    void shouldReturnCorrectSum() {
        //given
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(-2,1);
        //when
        Vector2d sum = v1.add(v2);
        //then
        assertEquals(new Vector2d(-1,3), sum);
    }



    @Test
    void shouldReturnCorrectDifference() {
        //given
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(-2,1);
        //when
        Vector2d diff = v1.subtract(v2);
        //then
        assertEquals(new Vector2d(3,1), diff);
    }



    @Test
    void shouldReturnOppositePoint() {
        //given
        Vector2d v = new Vector2d(-2,1);
        //when
        Vector2d opposite = v.opposite();
        //then
        assertEquals(new Vector2d(2,-1), opposite);
    }



    @Test
    void testEquals() {

        // first case -> x.equals(x) -> should return true
        //given
        Vector2d v = new Vector2d(-1, 2);
        Object o = v;
        //when
        boolean result = v.equals(o);
        //then
        assertEquals(true, result);


        //second case -> x.equals(null) -> should return false
        //given
        Object o2 = null;
        // v jw.
        //when
        boolean result2 = v.equals(o2);
        //then
        assertEquals(false, result2);


        //third case -> x.equals(y), x!=y -> should return false
        //given
        Vector2d v3 = new Vector2d(-1, -2);
        // o jw.
        //when
        boolean result3 = v3.equals(o);
        //then
        assertEquals(false, result3);


        //fourth case -> x.equals(y), x==y -> should return true
        //given
        Vector2d v4 = new Vector2d(-1, 2);
        // o jw.
        //when
        boolean result4 = v4.equals(o);
        //then
        assertEquals(true, result4);
    }



    @Test
    void shouldConvertToString() {
        //given
        Vector2d v = new Vector2d(-2,1);
        //when
        String vString = v.toString();
        //then
        assertEquals("(-2,1)", vString);
    }
}