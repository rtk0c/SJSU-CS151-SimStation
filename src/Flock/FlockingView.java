package Flock;

import mvc.Model;
import simstation.SimulationView;

import java.awt.*;

public class FlockingView extends SimulationView {
    public FlockingView(Model model) {
        super(model);
        setBackground(Color.GRAY);
    }
}
