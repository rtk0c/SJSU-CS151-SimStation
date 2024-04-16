package randomwalk;

import Plague.PlagueView;
import mvc.*;
import simstation.*;

class RandomWalkFactory extends SimulationFactory {
    public Model makeModel() { return new RandomWalkSimulation(); }
    public String getTitle() { return "Random Walks";}
    public View makeView(Model model) {return new RandomView(model);}

}
