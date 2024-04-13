package Plague;

import mvc.*;
        import randomwalk.*;
        import simstation.*;

public class PlagueSimulation extends Simulation {

    public static int VIRULENCE = 25; // % chance of infection
    public static int RESISTANCE = 2; // % chance of resisting infection

    public void populate() {
        for(int i = 0; i < 100; i++)

            addAgent(new Plague("Plague " + i));

    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new PlagueFactory());
        panel.display();
    }

}