package agh.cs.lab3;
import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab5.Grassfield;


import java.util.LinkedList;


public class World {

    public static void main(String[] args) {

        try {

            String[] array = {"f", "b", "r", "l", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
            LinkedList<MoveDirection> directions = new OptionParser().parse(array);
            IWorldMap map = new Grassfield(10);
            map.place(new Animal(map));
            map.place(new Animal(map, new Vector2d(3,4)));
            map.run(directions);
            System.out.println(map.toString());

        }
        catch(IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            System.exit(1);
        }


    }
}
