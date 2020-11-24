package agh.cs.lab5;


import agh.cs.lab2.Vector2d;
import agh.cs.lab7.MapBoundary;


import java.lang.Math;
import java.util.*;



public class Grassfield extends AbstractWorldMap {
    private MapBoundary boundary;
    protected HashMap<Vector2d, Grass> grassHashMap = new HashMap<>();
    Random generator = new Random();


    public Grassfield(int count) {


        for (int i=0;i<count;i++) {
            Grass grass;
            do {
                grass = new Grass(new Vector2d(generator.nextInt((int) Math.sqrt(count*10)),
                    generator.nextInt((int) Math.sqrt(count*10)))); }
            while (grassHashMap.containsKey(grass.getPosition()));
            grassHashMap.put(grass.getPosition(),grass);
        }

        this.boundary = new MapBoundary();

    }

    public MapBoundary getBoundary() {
        return boundary;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animalObjectAt(position).isPresent();
    }



    public Optional<Object> grassObjectAt(Vector2d position) {
        if (grassHashMap.containsKey(position))
            return Optional.of(grassHashMap.get(position));
        return Optional.empty();
    }




    @Override
    public Optional<Object> objectAt(Vector2d position) {
        if (animalObjectAt(position).isPresent())
            return animalObjectAt(position);
        return grassObjectAt(position);
    }



    @Override
    public Vector2d[] getExtremes() {

        Vector2d lowerCorner = boundary.getSortedX().getFirst().getSituation().lowerLeft(boundary.getSortedY().getFirst().getSituation());
        Vector2d upperCorner = boundary.getSortedX().getLast().getSituation().upperRight(boundary.getSortedY().getLast().getSituation());


        for (Grass grass: grassHashMap.values()) {
            Vector2d pos = grass.getPosition();

            lowerCorner = lowerCorner.lowerLeft(pos);
            upperCorner = upperCorner.upperRight(pos);

       }


        Vector2d[] result = {lowerCorner, upperCorner};
        return result;
    }


}
