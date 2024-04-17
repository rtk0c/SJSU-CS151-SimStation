package simstation;

import mvc.*;

public enum Heading {
    NORTH, SOUTH, EAST, WEST,
    ;

    // Store to avoid repeated allocating arrays on call to Heading.value()
    public static final Heading[] VALUES = values();

    public static Heading parse(String heading) {
        return switch (heading.toLowerCase()) {
            case "north" -> NORTH;
            case "south" -> SOUTH;
            case "east" -> EAST;
            case "west" -> WEST;
            default -> {
                Utilities.error("Invalid heading: " + heading);
                yield null;
            }
        };
    }

    public static Heading random() {
        int i = Utilities.rng.nextInt(VALUES.length);
        return VALUES[i];
    }
}
