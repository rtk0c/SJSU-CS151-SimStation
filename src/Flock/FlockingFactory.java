package Flock;

import mvc.Command;
import mvc.Model;
import simstation.*;

public class FlockingFactory  extends SimulationFactory{

    public Model makeModel() { return new FlockingSimulation(); }
    public String getTitle() { return "Flocking Simulation";}


}
