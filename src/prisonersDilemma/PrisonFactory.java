package prisonersDilemma;

import mvc.*;
import simstation.*;

public class PrisonFactory extends SimulationFactory {
    @Override
    public Model makeModel() { return new PrisonSimulation(); }
}
