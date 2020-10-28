package agh.cs.lab3;
import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;

import java.util.LinkedList;

import java.util.Scanner;

public class World {

    public static void run(String[] array, Animal animal) {

        LinkedList<MoveDirection> list = OptionParser.parse(array);

        for (MoveDirection direction: list) {
            animal.move(direction);

            switch(direction) {
                case FORWARD:
                    System.out.println("Zwierzak idzie do przodu"); break;
                case BACKWARD:
                    System.out.println("Zwierzak idzie do tyłu"); break;
                case LEFT:
                    System.out.println("Zwierzak obraca sie w lewo"); break;
                case RIGHT:
                    System.out.println("Zwierzak obraca sie w prawo"); break;
            };

            System.out.println("Aktualna pozycja: " + animal.getSituation());
            System.out.println("Aktualny zwrot: " + animal.getDirection());
        }
    }




    public static void main(String[] args) {

        /*Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));


        System.out.println(MapDirection.NORTH.toString());
        System.out.println(MapDirection.NORTH.next());
        System.out.println(MapDirection.NORTH.previous());
        System.out.println(MapDirection.NORTH.toUnitVector());*/


        /*System.out.println(animal.getDirection());
        System.out.println(animal.getSituation());
        animal.move(MoveDirection.LEFT);
        for (int i=0; i<3; i++) {
            animal.move(MoveDirection.FORWARD);
        }
        System.out.println(animal.getSituation());
        animal.move(MoveDirection.BACKWARD);
        System.out.println(animal.getSituation());*/

        Animal animal = new Animal();
        System.out.println("Pozycja zwierzaka: " + animal.getSituation());

        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj ilosc krokow: ");
        Integer n = scan.nextInt();
        String[] array = new String[n];

        System.out.println("Podaj n kolejnych kierunkow (w osobnych liniach): f/forward, b/backward, l/left, r/right");
        for (int i=0; i<n; i++) {
            array[i]=scan.nextLine();
        }

        //String[] array = {"f", "l", "backward", "h", "right"};
        run(array,animal);


    }
}
