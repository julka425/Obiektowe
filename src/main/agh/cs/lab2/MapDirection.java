package agh.cs.lab2;



public enum MapDirection {
    NORTH, SOUTH, WEST, EAST;


    public String toString() {
        switch (this) {
            case NORTH: return "Polnoc";
            case SOUTH: return "Poludnie";
            case WEST: return"Zachod";
            case EAST: return "Wschod";
        };
        throw new NullPointerException();
    }



    public MapDirection next() {
         switch (this) {
            case NORTH: return EAST;
            case EAST: return SOUTH;
            case SOUTH: return WEST;
            case WEST: return NORTH;
        };
         throw new NullPointerException();
    }



    public MapDirection previous() {
        switch (this) {
            case NORTH: return WEST;
            case EAST: return NORTH;
            case SOUTH: return EAST;
            case WEST: return SOUTH;
        };
        throw new NullPointerException();
    }



    public Vector2d toUnitVector() {
        switch (this) {
            case NORTH: return new Vector2d(0,1);
            case EAST: return new Vector2d(1,0);
            case SOUTH: return new Vector2d(0,-1);
            case WEST: return new Vector2d(-1,0);
        };
        throw new NullPointerException();
    }
}




