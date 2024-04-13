package Plague;
import mvc.*;

import simstation.*;

public class PlagueFactory  extends SimulationFactory{

    public Model makeModel() {  return new PlagueSimulation(); }
    public View makeView(Model model) {return new PlagueView(model);}
    public String getTitle() { return "Flocking Simulation";}



}
