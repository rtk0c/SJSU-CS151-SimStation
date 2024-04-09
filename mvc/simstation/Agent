package simstation;

import java.io.Serializable;

// TODO Serializable, Runnable own files?
abstract class Agent implements Serializable, Runnable {
    String name;
    //Heading heading; // TODO
    int xc;
    int yc;
    boolean suspended = false;
    boolean stopped = false;
    Thread myThread;

    public abstract void run();

    public abstract void start();

    public abstract void suspend();

    public abstract void resume();

    public abstract void stop();

    public abstract void update();

    public abstract void move(int steps);





}
