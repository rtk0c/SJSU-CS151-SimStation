package simstation;

import java.io.*;

// TODO Serializable, Runnable own files?
//Drunk extends this class
//Agent is runners of thread

public abstract class Agent implements Serializable, Runnable {
    protected Heading heading;
    protected Simulation world;

    private String name;
    private int xc = 100;
    private int yc = 100;
    // Lifecycle of an agent:
    // - Initialization (ctor): marked as started but is not running since no thread has been spawned
    // - Starting (Simulation.start()): a thread is spawned to house this Agent; executes the update loop in run()
    // - Suspending (this.suspend()): pause execution
    // - Resuming (this.resume()): resume execution
    // - Stopping (Simulation.stop()): this.stopped is set to true, and in the next iteration of the update loop in run() exits the thread
    boolean suspended;
    boolean stopped;

    protected transient Thread myThread;

    public Agent(String name) {
        this.name = name;
        suspended = false;
        stopped = false;
        myThread = null;
    }

    public Simulation getWorld() { return world; }
    public void setWorld(Simulation world) { this.world = world; }

    public String getName() { return name; }

    public void setX(int xc) { this.xc = xc; }
    public int getX() { return xc; }
    public void setY(int yc) { this.yc = yc; }
    public int getY() { return yc; }

    public void move(int steps) {
        int dx = 0;
        int dy = 0;
        switch (heading) {
            case NORTH -> dy = -steps;
            case SOUTH -> dy = +steps;
            case EAST -> dx = +steps;
            case WEST -> dx = -steps;
        }

        this.xc += dx;
        this.yc += dy;
        world.changed();
    }

    public synchronized String toString() {
        String result = name;
        if (stopped) result += " (stopped)";
        else if (suspended) result += " (suspended)";
        else result += " (running)";
        return result;
    }

    public synchronized void stop() {
        stopped = true;
    }
    // used to check stopped
    public synchronized boolean isStopped() {
        return stopped;
    }

    public synchronized void suspend() {
        suspended = true;
    }

    public synchronized boolean isSuspended() {
        return suspended;
    }

    public synchronized void resume() {
        notify();
    }

    public synchronized void join() {
        try {
            if (myThread != null) myThread.join();
        } catch (InterruptedException e) {
            world.println(e.getMessage());
        }
    }

    // wait for notification if I'm not stopped and I am suspended
    private synchronized void checkSuspended() {
        try {
            while (!stopped && suspended) {
                wait();
                suspended = false;
            }
        } catch (InterruptedException e) {
            world.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        myThread = Thread.currentThread();
        while (!isStopped()) {
            try {
                update();
                Thread.sleep(20);
                checkSuspended();
            } catch (InterruptedException e) {
                world.println(e.getMessage());
            }
        }
        world.println(name + " stopped");
    }

    // TODO(rtk0c): there is no need for this method; when each agent's Thread is start()'ed, run() is called and this.stopped is initialized to false; there is no way to restart an agent either (the thread would have exited immediately after this.stopped = true)
    //              but then the UML diagram prescribes this thing?
//    public void start() {
//    }

    public abstract void update();

    // Agent is runner inside of thread
    // call move method 10 times to see movement rather
    // than teleporting to 10th spot
    // move calls world.changed()
}
