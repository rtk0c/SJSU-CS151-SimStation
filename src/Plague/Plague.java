package Plague;

import mvc.*;
import simstation.*;
import java.awt.*;
import java.util.Iterator;

class Plague extends Agent {
    private boolean infected;

    public Plague(String name) {

        super(name);

        //TODO check bounds?
        this.setX(Utilities.rng.nextInt(350));
        this.setY(Utilities.rng.nextInt(475));
        heading = Heading.random();
        //TODO start random?
        infected = Utilities.rng.nextBoolean();;
    }

    public void update() {
        Plague randNeighbor = (Plague) world.getNeighbor(this, 1);
        if (randNeighbor != null && !randNeighbor.isInfected())
        {
            //System.out.println("Called");
            //TODO check math cant wrap head for making these percentages based in
            // plague simulation

            boolean infect = Utilities.rng.nextInt(101) < PlagueSimulation.VIRULENCE;
            boolean resist = Utilities.rng.nextInt(101) < PlagueSimulation.RESISTANCE;

            //if both not hit keep true
            if (infect && !resist)
            {
                randNeighbor.setInfected(true);
            }
        }

        heading = Heading.random();
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);

    }

    // allowed to have getters/setters?
    public boolean isInfected() {
        return infected;
    }

    public void setInfected(boolean infected) {
        this.infected = infected;
    }

}
