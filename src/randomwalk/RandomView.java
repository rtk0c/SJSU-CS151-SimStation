package randomwalk;

import mvc.Model;
import simstation.SimulationView;

import java.awt.*;

public class RandomView extends SimulationView {
    public RandomView(Model model) {
        super(model);
        setBackground(Color.GRAY);
    }
}
