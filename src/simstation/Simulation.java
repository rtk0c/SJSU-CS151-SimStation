package simstation;

import java.util.*;
import mvc.*;

public class Simulation extends Model {

    transient private Timer timer; // timers aren't serializable
    private int clock = 0;
    //has zero or more agents

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    private class ClockUpdater extends TimerTask {
        public void run() {
            clock++;
        }
    }

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
        //TODO double check returns random agent

        return a;
    }

    public void populate()
    {
        //no op
    }

}
