package agh.cs.lab3;
import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab4.IWorldMap;

import java.util.List;
import java.util.Objects;
import java.lang.IllegalArgumentException;
import java.util.Optional;

public class Animal {
    private MapDirection direction;
    private Vector2d situation;
    private IWorldMap map;


    public Animal() {
        this.situation = new Vector2d(2,2);
        this.direction = MapDirection.NORTH;
    }


    public Animal(IWorldMap map) {
        this.situation = new Vector2d(2,2);
        this.direction = MapDirection.NORTH;
        map.place(this);
        this.map=map;
    }


    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.situation = initialPosition;
        this.direction = MapDirection.NORTH;
        map.place(this);
        this.map=map;
    }


    public MapDirection getDirection() {
        return direction;
    }

    public Vector2d getSituation() {
        return situation;
    }



    public void movePosition(MoveDirection direction) {
        Vector2d v;
        if (direction == MoveDirection.FORWARD)
            v = getSituation().add(getDirection().toUnitVector());

        else
            v = getSituation().subtract(getDirection().toUnitVector());

        if (map.canMoveTo(v))
            this.situation = v;
    }



    public void move(MoveDirection direction) {

        switch (direction) {

            case RIGHT: {
                this.direction = this.direction.next();
                break;
            }
            case LEFT: {
                this.direction = this.direction.previous();
                break;
            }

            case FORWARD:
            case BACKWARD:
                movePosition(direction);
        }
    }


    @Override
    public String toString() {
        switch (this.direction) {
            case NORTH:
                return "∧";
            case SOUTH:
                return "∨";
            case EAST:
                return ">";
            case WEST:
                return "<";
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return direction == animal.direction &&
                Objects.equals(situation, animal.situation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, situation);
    }
}
