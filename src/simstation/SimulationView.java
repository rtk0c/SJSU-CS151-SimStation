package simstation;

import java.awt.*;
import mvc.*;


public class SimulationView extends View {
    public SimulationView(Model model) {
        super(model);
    }
    //TODO SimulationView gets list of agents

    /*public void update()
    {
        repaint();
    }*/

    @Override
    public void paintComponents(Graphics gc) {
        super.paintComponents(gc);
        // TODO draws filled in circle for each agent
        //gc.drawOval(X, Y, r, r);

    }
}
