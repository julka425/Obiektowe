package agh.cs.lab3;

import agh.cs.lab2.MoveDirection;

import java.util.LinkedList;

public class OptionParser {

    public static LinkedList<MoveDirection> parse(String[] args) {
        LinkedList <MoveDirection> list = new LinkedList<>();

        for (String arg: args) {

            if ("f".equals(arg) || "forward".equals(arg)) {
                list.add(MoveDirection.FORWARD);
            }

            else if ("b".equals(arg) || "backward".equals(arg)) {
                list.add(MoveDirection.BACKWARD);
            }

            else if ("l".equals(arg) || "left".equals(arg)) {
                list.add(MoveDirection.LEFT);
            }

            else if ("r".equals(arg) || "right".equals(arg)) {
                list.add(MoveDirection.RIGHT);
            }

            else throw new IllegalArgumentException(arg + " is not legal move specification");

        }
        return list;

    }

}
