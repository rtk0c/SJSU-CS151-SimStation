package Flock;

import mvc.*;
import randomwalk.*;
import simstation.*;

public class FlockingSimulation extends Simulation {

    public void populate() {
        for(int i = 0; i < 150; i++)

            addAgent(new Bird("Bird " + i));
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new FlockingFactory());
        panel.display();
    }

}