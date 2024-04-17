package simstation;

import java.io.*;

public abstract class Agent implements Serializable, Runnable {
    protected Heading heading;
    protected Simulation world;

    private final String name;
    private int xc = 100;
    private int yc = 100;
    // Lifecycle of an agent:
    // - Initialization (ctor): marked as started but is not running since no thread has been spawned
    // - Starting (Simulation.start()): a thread is spawned to house this Agent; executes the update loop in run()
    // - Suspending (this.suspend()): pause execution
    // - Resuming (this.resume()): resume execution
    // - Stopping (Simulation.stop()): this.stopped is set to true, and in the next iteration of the update loop in run() exits the thread
    boolean suspended = true;
    // NOTE(rtk0c): I would name this field "running" since it really is just a condition variable to signal the agent to stop its update loop
    //              But the assignment gave skeletal code named this... yeah
    boolean stopped = true;

    // NOTE(rtk0c): this field has absolutely no purpose, and actually hinders Agent from being used in a thread pool,
    //              Thread.currentThread() is perfectly fast--in fact it is just a read from a hardware register on HotSpot JVM.
    //              But I guess the assignment UML diagram has it, so we'll just leave it there
    protected transient Thread myThread;

    public Agent(String name) {
        this.name = name;
    }

    public Simulation getWorld() { return world; }
    public void setWorld(Simulation world) { this.world = world; }

    public String getName() { return name; }

    public void setX(int xc) {
        // Implement world coordinate wrap-around
        if (world != null)
            this.xc = Math.floorMod(xc, world.getWidth());
        else this.xc = xc;
    }
    public int getX() { return xc; }
    public void setY(int yc) {
        if (world != null)
            this.yc = Math.floorMod(yc, world.getHeight());
        else this.yc = yc;
    }
    public int getY() { return yc; }

    // FIXME: writes to this.xc/yc are not synchronized, so readers (other Agent or Simulation) may read partially
    //        updated positions, where either xc or yc are the old value
    //        (the assignment doesn't seem to care so...)
    public void move(int steps) {
        int dx = 0;
        int dy = 0;
        switch (heading) {
            case NORTH -> dy = -steps;
            case SOUTH -> dy = +steps;
            case EAST -> dx = +steps;
            case WEST -> dx = -steps;
        }

        setX(xc + dx);
        setY(yc + dy);
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

    public void start() {
        // NOTE(rtk0c): this allows us to run the same Agent multiple times, although the assignment didn't prescribe this
        if (stopped) {
            stopped = false;
            suspended = false;
            new Thread(this).start();
        } else {
            throw new IllegalStateException("Running the Agent twice is not supported");
        }
    }

    public void join() {
        try {
            if (myThread != null) myThread.join();
        } catch (InterruptedException e) {
            world.println(e.getMessage());
        }
    }

    // When an object is deserialized using ObjectInputStream, if a method with this signature is present, it is called
    // We use this to recreate the Threads for each Agent
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        if (!stopped) {
            assert suspended;
            // Immediately put myself into a suspended state
            new Thread(this).start();
        }
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException, ClassNotFoundException {
        boolean oldSuspended = suspended;
        // TODO this breaks the invariant that onSuspend should always be called before suspend
        //   (to an Agent deserialized, it is as-if onSuspend had not been called and onResume gets called)
        this.suspended = true;
        out.defaultWriteObject();
        this.suspended = oldSuspended;
    }

    // wait for notification if I'm not stopped and I am suspended
    private void checkSuspended() {
        try {
            while (!stopped && suspended) {
                synchronized (this) {
                    wait();
                    suspended = false;
                }
            }
        } catch (InterruptedException e) {
            onInterrupted();
            world.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        myThread = Thread.currentThread();
        onStart();
        // Support starting in the suspended state
        checkSuspended();
        while (!isStopped()) {
            try {
                update();
                Thread.sleep(20);
                checkSuspended();
            } catch (InterruptedException e) {
                onInterrupted();
                world.println(e.getMessage());
            }
        }
        onExit();
        world.println(name + " stopped");
    }

    public abstract void update();

    protected void onStart() {}
    protected void onInterrupted() {}
    protected void onExit() {}

    // Agent is runner inside of thread
    // call move method 10 times to see movement rather
    // than teleporting to 10th spot
    // move calls world.changed()
}
