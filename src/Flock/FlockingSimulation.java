package Flock;

import java.util.*;
import mvc.*;
import simstation.*;

public class FlockingSimulation extends Simulation {

    public void populate() {
        for (int i = 0; i < 50; i++)
            addAgent(new Bird("Bird " + i));
    }

    @Override
    public String stats() {
        HashMap<Integer, Integer> count = new HashMap<>(); // <spd, count>
        for (Agent a : getAgents()) {
            Bird current = (Bird) a;
            System.out.println(current.getSpeed());
            if (!count.containsKey(current.getSpeed())) {
                count.put(current.getSpeed(), 0);
            }
            count.put(current.getSpeed(), count.get(current.getSpeed()) + 1);
        }
        StringBuilder ret = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            ret.append("#birds @ speed ").append(entry.getKey()).append(" = ").append(entry.getValue()).append("\n");
        }
        ;
        return ret.toString();
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new FlockingFactory());
        panel.display();
    }

}