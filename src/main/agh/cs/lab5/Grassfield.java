package agh.cs.lab5;


import agh.cs.lab2.Vector2d;


import java.lang.Math;
import java.util.*;



public class Grassfield extends AbstractWorldMap {
    private int count;
    private final ArrayList<Grass> grassList;

    Random generator = new Random();


    public Grassfield(int count) {
        this.count = count;
        this.grassList = new ArrayList<>();


        for (int i=0;i<count;i++) {
            grassList.add(new Grass(new Vector2d(generator.nextInt((int) Math.sqrt(count*10)),
                    generator.nextInt((int) Math.sqrt(count*10)))));
        }

    }



    public ArrayList<Grass> getGrassList() {
        return grassList;
    }



    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animalObjectAt(position).isPresent();
    }



    public Optional<Object> grassObjectAt(Vector2d position) {
        for (Grass grass: grassList) {
            if (grass.getPosition().equals(position))
                return Optional.of(grass);
        }
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
        int maxX = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;

        int maxY = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;


        for (int i=0; i< getAnimals().size(); i++) {
            Vector2d pos = getAnimals().get(i).getSituation();

            maxX = Math.max(maxX, pos.x);
            minX = Math.min(minX, pos.x);
            maxY = Math.max(maxY, pos.y);
            minY = Math.min(minY, pos.y);
       }

        for (Grass grass: grassList) {
            Vector2d pos = grass.getPosition();

            maxX = Math.max(maxX, pos.x);
            minX = Math.min(minX, pos.x);
            maxY = Math.max(maxY, pos.y);
            minY = Math.min(minY, pos.y);
       }

        Vector2d[] result = new Vector2d[2];
        result[0] = new Vector2d(minX, minY);
        result[1] = new Vector2d(maxX, maxY);
        return result;
    }


}
