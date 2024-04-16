package Flock;

import mvc.Command;
import mvc.Model;
import mvc.View;
import randomwalk.RandomView;
import simstation.*;

public class FlockingFactory  extends SimulationFactory{

    public Model makeModel() { return new FlockingSimulation(); }
    public String getTitle() { return "Flocking Simulation";}
    public View makeView(Model model) {return new FlockingView(model);}



}
