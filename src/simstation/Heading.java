package simstation;

public enum Heading {
    NORTH, SOUTH, EAST, WEST,
    ;

    public static Heading parse(String heading) {
        switch (heading) {
            case "north":
                return NORTH;
            case "south":
                return SOUTH;
            case "east":
                return EAST;
            case "west":
                return WEST;
        }
        throw new IllegalArgumentException();
    }
}
