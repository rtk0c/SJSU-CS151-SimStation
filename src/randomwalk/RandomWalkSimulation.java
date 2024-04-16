package randomwalk;

import mvc.*;
import randomwalk.*;
import simstation.*;

import java.awt.*;

public class RandomWalkSimulation extends Simulation {

    public void populate() {
        for(int i = 0; i < 15; i++)
            addAgent(new Drunk("Drunk man " + i));
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new RandomWalkFactory());
        panel.display();
    }

}
