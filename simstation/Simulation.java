package simstation;
import mvc.*;

public class Simulation extends Model {

    int clock = 0;

    public void start() {
        changed();
    }

    public void stats() {
        changed();
    }

    public void stop() {
        changed();
    }

    public void suspend() {
        changed();
    }

    public void resume() {
        changed();
    }

    public Agent getNeighbor(Agent a, Double radius)
    {
        //TODO double check returns agent
        return a;
    }

    public void populate()
    {

    }

}
