package simstation;

import mvc.*;

public class StatsCommand extends Command {
    public StatsCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        Simulation sim = (Simulation) getModel();
        String message = sim.stats();

        Utilities.inform(message);
    }
}