package agh.cs.lab7;

import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;

public interface IPositionChangeObserver {
    void positionChanged(Animal animal, Vector2d oldPosition, Vector2d newPosition);

}
