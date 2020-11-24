package agh.cs.lab3;
import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab5.Grassfield;
import agh.cs.lab7.IPositionChangeObserver;
import agh.cs.lab7.IPositionChangedPublisher;


import java.util.LinkedList;
import java.util.Objects;
import java.lang.IllegalArgumentException;



public class Animal implements IPositionChangeObserver, IPositionChangedPublisher {
    private MapDirection direction;
    private Vector2d situation;
    private IWorldMap map;
    private LinkedList<IPositionChangeObserver> observers = new LinkedList<>();


    public Animal() {
        this.situation = new Vector2d(2,2);
        this.direction = MapDirection.NORTH;
    }

    public Animal(IWorldMap map) {
        this(map, new Vector2d(2,2));
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.situation = initialPosition;
        this.direction = MapDirection.NORTH;

        if (!map.place(this))
            throw new IllegalArgumentException("couldn't place an animal on " + this.situation);
        this.map = map;

        addObserver((IPositionChangeObserver) map);

        if (map.getClass() == Grassfield.class) {
            ((Grassfield) map).getBoundary().upgrade(this);
            addObserver((IPositionChangeObserver) ((Grassfield) map).getBoundary());
        }
    }



    @Override
    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void positionChanged(Animal animal, Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver observer: observers) {
            observer.positionChanged(animal, oldPosition, newPosition);
        }
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
            v = this.situation.add(this.direction.toUnitVector());

        else
            v = this.situation.subtract(this.direction.toUnitVector());

        if (map.canMoveTo(v)) {
            Vector2d position = situation;
            this.situation = v;
            positionChanged(this,position,v);
        }
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
