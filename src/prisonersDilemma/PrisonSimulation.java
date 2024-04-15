package prisonersDilemma;

import mvc.*;
import simstation.*;

public class PrisonSimulation extends Simulation {
    @Override
    public void populate() {
        for (int i = 0; i < 10; i++) {
            for (var type : Strategy.Type.VALUES) {
                String name = "prisoner" + i + "-" + type.name().toLowerCase();
                addAgent(new Prisoner(name, type));
            }
        }
    }

    @Override
    public String stats() {
        // TODO
        return super.stats();
    }

    public static void main(String[] args) {
        var app = new AppPanel(new PrisonFactory());
        app.display();
    }
}
