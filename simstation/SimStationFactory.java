package simstation;
import mvc.*;


public class SimStationFactory implements AppFactory {
    @Override
    public Model makeModel() {
        return new Simulation();
    }

    @Override
    public View makeView(Model model) {
        return new SimulationView(model);
    }

    @Override
    public String getTitle() {
        return "SimStation";
    }

    @Override
    public String[] getHelp() {
        return new String[] {"Start: Starts the simulation",
                "Suspend: Halts simulation",
                "Resume: Resumes simulation",
                "Stop: Stops simulation," +
                "Stats: Shows stats for that customization "};

    }

    @Override
    public String about() {
        return "Simstation 1.0";
    }

    @Override
    public String[] getEditCommands() {
        return new String[] {"Start", "Suspend", "Resume", "Stop", "Stats"};
    }

    @Override
    public Command makeEditCommand(Model model, String type) {
        if (type.equals("Start")) return new StartCommand(model);
        if (type.equals("Suspend")) return new SuspendCommand(model);
        if (type.equals("Resume")) return new ResumeCommand(model);
        if (type.equals("Stop")) return new StopCommand(model);
        if (type.equals("Stats")) return new StatsCommand(model);

        return null;
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        return null;
    }
}
