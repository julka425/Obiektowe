package agh.cs.lab4;


import agh.cs.lab2.Vector2d;
import agh.cs.lab5.AbstractWorldMap;

import java.util.*;


public class RectangularMap extends AbstractWorldMap {
    private final int width;
    private final int height;


    private final static int LOWER = 0;



    public RectangularMap(int width, int height) {
        this.height = height;
        this.width = width;
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.x >= LOWER && position.x < width &&
                position.y >= LOWER && position.y < height)
            return (!isOccupied(position));
        return false;
    }



    @Override
    public Optional<Object> objectAt(Vector2d position) {
        return animalObjectAt(position);
    }



    @Override
    public Vector2d[] getExtremes() {
        Vector2d[] result = new Vector2d[2];
        result[0] = new Vector2d(LOWER,LOWER);
        result[1] = new Vector2d(width - 1,height - 1);
        return result;
    }

}
