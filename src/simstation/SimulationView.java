package simstation;

import java.awt.*;
import mvc.*;


public class SimulationView extends View {
    public SimulationView(Model model) {
        super(model);
    }
    //TODO SimulationView gets list of agents

    @Override
    public void update() {
        repaint();
    }

    @Override
    public void paintComponents(Graphics gc) {
        super.paintComponents(gc);
        var oldColor = gc.getColor();

        var sim = ((Simulation) model);
        gc.setColor(Color.WHITE);
        for (Agent agent : sim.getAgents()) {
            int x = agent.getX();
            int y = agent.getY();
            gc.fillOval(x, y, 10, 10);
        }

        gc.setColor(oldColor);
    }
}
