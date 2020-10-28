package agh.cs.lab3;
import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;

import java.util.Objects;

public class Animal {
    private MapDirection direction;
    private Vector2d situation;


    public Animal() {
        setSituation(new Vector2d(2,2));
        setDirection(MapDirection.NORTH);
    }



    public MapDirection getDirection() {
        return direction;
    }

    public void setDirection(MapDirection direction) {
        this.direction = direction;
    }

    public Vector2d getSituation() {
        return situation;
    }

    public void setSituation(Vector2d situation) {
        this.situation = situation;
    }



    public void move(MoveDirection direction) {

        switch (direction) {

            case RIGHT: {
                this.setDirection(getDirection().next());
                break;
            }
            case LEFT: {
                this.setDirection(getDirection().previous());
                break;
            }
            case FORWARD:  {
                Vector2d v = getSituation().add(getDirection().toUnitVector());
                if ((v.x >= 0) && (v.x <= 4) && (v.y >= 0) && (v.y <= 4))
                        this.setSituation(v);
                break;
            }
            case BACKWARD: {
                Vector2d v = getSituation().subtract(getDirection().toUnitVector());
                if ((v.x >= 0) && (v.x <= 4) && (v.y >= 0) && (v.y <= 4))
                    this.setSituation(v);
                break;
            }
        }
    }


    @Override
    public String toString() {
        return "Animal{" +
                "direction=" + direction +
                ", situation=" + situation +
                '}';
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
