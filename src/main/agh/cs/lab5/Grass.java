package agh.cs.lab5;

import agh.cs.lab2.Vector2d;

import java.util.Objects;

public class Grass {
    private Vector2d position;

    public Grass(Vector2d position) {
        this.position = position;
    }

    public Vector2d getPosition() {
        return position;
    }


    @Override
    public String toString() {
        return "*";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grass grass = (Grass) o;
        return Objects.equals(position, grass.position);
    }


    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
