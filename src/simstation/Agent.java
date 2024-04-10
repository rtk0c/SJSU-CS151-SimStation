package simstation;

import java.io.Serializable;

// TODO Serializable, Runnable own files?
//Drunk extends this class
//Agent is runners of thread

abstract class Agent implements Serializable, Runnable {
    private String name;
    //Heading heading; // TODO
    //Simulation world;
    private int xc;
    private int yc;
    boolean suspended = false;
    boolean stopped = false;
    private Thread myThread;


    public synchronized void suspend() { suspended = true; }
    public synchronized void resume() { notify(); }
    public synchronized void stop() { stopped = true; }
    // used to check stopped
    public synchronized boolean isStopped() { return stopped; }


    public void run() {
        myThread = Thread.currentThread();


    }

    public void start() {

    }
    // abstract
    public abstract void update();

    public void move(int steps) {

    }


    // Agent is runner inside of thread
    // call move method 10 times to see movement rather
    // than teleporting to 10th spot
    // move calls world.changed()



}
