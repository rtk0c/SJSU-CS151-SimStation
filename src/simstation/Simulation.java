package simstation;

import java.util.*;
import mvc.*;

public class Simulation extends Model {

    private transient Timer timer; // timers aren't serializable
    private int clock = 0;
    private List<Agent> agents = new ArrayList<>();

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    public int getCurrentCycle() {
        return clock;
    }

    // provides agents with thread safe access to stdout
    private class ClockUpdater extends TimerTask {
        public void run() {
            clock++;
        }
    }

    public synchronized void println(String message) {
        // TODO(rtk0c): do we want to use a decorated stdout, ala Console class in Agent Lab?
        System.out.println(message);
    }

    public void start() {
        stop();
        populate();
        for (var agent : agents) {
            new Thread(agent).start();
        }
        changed();
    }

    public String stats() {
        String res = "";
        res += "#agents = " + agents.size() + '\n';
        res += "clock = " + clock + '\n';
        return res;
    }

    public void stop() {
        for (Agent agent : agents) {
            agent.stop();
        }
        agents.clear();
        changed();
    }

    public void suspend() {
        for (Agent agent : agents) {
            agent.suspend();
        }
        changed();
    }

    public void resume() {
        for (Agent agent : agents) {
            agent.resume();
        }
        changed();
    }

    public Agent getNeighbor(Agent a, double radius) {
        // Doing distance comparison between two points:
        //     sqrt((x1 - x2)^2 + (y1 - y2)^2) <= r
        // we can square both sides to get:
        //     (x1 - x2)^2 + (y1 - y2)^2 <= r^2
        // to save a sqrt() computation on every iteration

        double radiusSq = radius * radius;

        // "An efficient implementation of getNeighbor picks a random location in the agents list. Starting at this location it visits each agent in order (wrapping around to the start if necessary) until it either finds a suitable neighbor or until it loops back to the starting point and returns null."
        for (int agentsSize = agents.size(), i = Utilities.rng.nextInt(0, agentsSize);
             i < agentsSize;
             i = (i + 1) % agentsSize) {

            Agent that = agents.get(i);
            int deltaX = a.getX() - that.getX();
            int deltaY = a.getY() - that.getY();
            int distSq = deltaX * deltaY + deltaY * deltaY;
            if (distSq <= radiusSq) {
                return that;
            }
        }
        return null;
    }

    public void populate() {
        //no op
    }

    protected void addAgent(Agent agent) {
        agent.setWorld(this);
        this.agents.add(agent);
    }

    public List<Agent> getAgents() {
        return agents;
    }
}
