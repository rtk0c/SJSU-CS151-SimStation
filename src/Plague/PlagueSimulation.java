package Plague;

import mvc.*;
        import randomwalk.*;
        import simstation.*;

public class PlagueSimulation extends Simulation {

    public static int VIRULENCE = 5; // % chance of infection
    public static int RESISTANCE = 90; // % chance of resisting infection

    public void populate() {
        addAgent(new Plague("Plague 0" , true));
        for(int i = 1; i < 100; i++)
            addAgent(new Plague("Plague " + i, false));
    }

    @Override
    public String stats(){
        double size = getAgents().size();
        double count = 0.0;
        for (Agent a :getAgents()) {
            Plague current = (Plague) a;
            if (current.isInfected()) count++;
        }

        return "#agents = " + size + "\nclock = " + getCurrentCycle() + "\n%infected = " + (count / size);
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new PlagueFactory());
        panel.display();
    }

}