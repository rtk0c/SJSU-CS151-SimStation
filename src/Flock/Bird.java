package Flock;

import mvc.*;
import simstation.*;
import java.awt.*;
import java.util.Iterator;

class Bird extends Agent {

    private int speed;
    public Bird(String name) {
        super(name);
        // Suppossed to add setter?

        this.setX(Utilities.rng.nextInt(350));
        this.setY(Utilities.rng.nextInt(475));
        // start random, each has heading + speed
        heading = Heading.random();
        speed = Utilities.rng.nextInt(10);

    }




    public void update() {

        Bird randNeighbor = (Bird) world.getNeighbor(this, 1);

        this.speed = randNeighbor.speed;
        this.heading = randNeighbor.heading;

        int steps = Utilities.rng.nextInt(10) + 1;

        move(steps);

    }}



