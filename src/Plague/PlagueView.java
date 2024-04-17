package Plague;

import java.awt.*;
import mvc.*;
import simstation.*;

public class PlagueView extends View {

    public PlagueView(Model model) {
        super(model);
    }

    @Override
    public void paint(Graphics gc) {
        super.paint(gc);
        var sim = (PlagueSimulation) model;
        for (Agent agent : sim.getAgents()) {
            Plague plague = (Plague) agent;
            Color color;
            if (plague.isInfected()) {
                color = Color.RED; // infected color
            } else {
                color = Color.GREEN; // No infect color
            }
            gc.setColor(color);
            int x = agent.getX();
            int y = agent.getY();
            gc.fillOval(x, y, 10, 10);
        }
    }
}
