package prisonersDilemma;

import Plague.*;
import simstation.*;

public class Prisoner extends Agent {
    private int fitness = 0;
    private boolean partnerCheated = false;
    private final Strategy strategy;

    public Prisoner(String name, Strategy.Type strategyType) {
        super(name);
        this.strategy = Strategy.makeStrategy(strategyType, this);
    }

    public boolean cooperate() {
        return strategy.cooperate();
    }

    @Override
    public void update() {
        var partner = ((Prisoner) world.getNeighbor(this, 1));

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

    public void updateFitness(int amt) {
        this.fitness += amt;
    }

    public boolean hasPartnerCheated() { return partnerCheated; }
}
