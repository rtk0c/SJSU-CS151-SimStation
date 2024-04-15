package prisonersDilemma;

import java.util.concurrent.atomic.*;
import mvc.*;
import simstation.*;

// ==== Thread Safety ====
// Each Prisoner periodically runs update() on its own thread, as implemented by the simstation framework
// In the prisoner's dilemma logic, there is 3 mutations: this.partnerCheated, this.fitness, and partner.fitness
// Out of these, writes to this.partnerCheated are a single set to a boolean, so in JVM memory model it is already atomic; no synchronization primitive needed
// writes to (this|partner).fitness are increments, so that is guarded in an AtomicInt to guarantee reads won't get a partially updated

public class Prisoner extends Agent {
    private AtomicInteger fitness = new AtomicInteger(0);
    private boolean partnerCheated = false;
    private final Strategy strategy;

    public Prisoner(String name, Strategy.Type strategyType) {
        super(name);
        strategy = Strategy.makeStrategy(strategyType, this);
        heading = Heading.random();
    }

    @Override
    public void update() {
        // Prisoner walks around randomly
        heading = Heading.random();
        move(Utilities.rng.nextInt(10) + 1);

        var partner = ((Prisoner) world.getNeighbor(this, 10));

        boolean thisCoop = this.cooperate();
        boolean partnerCoop = partner.cooperate();

        if (thisCoop && partnerCoop) {
            this.updateFitness(1);
            partner.updateFitness(1);
        } else if (thisCoop && !partnerCoop) {
            partner.updateFitness(5);
        } else if (!thisCoop && partnerCoop) {
            this.updateFitness(5);
        } else if (!thisCoop && !partnerCoop) {
            this.updateFitness(3);
            partner.updateFitness(3);
        }

        partnerCheated = !partnerCoop;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    /* read-only */
    public int getFitness() { return fitness.get(); }
    /* mutating */
    public void updateFitness(int amt) { fitness.addAndGet(amt); }

    /* read-only */
    public boolean cooperate() {
        return strategy.cooperate();
    }

    /* read-only */
    public boolean hasPartnerCheated() { return partnerCheated; }
}
