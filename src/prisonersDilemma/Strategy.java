package prisonersDilemma;

import java.io.*;
import mvc.*;

public abstract class Strategy implements Serializable {
    protected Prisoner myPrisoner;

    public Prisoner getPrisoner() { return myPrisoner; }
    public void setPrisoner(Prisoner myPrisoner) { this.myPrisoner = myPrisoner; }

    public abstract boolean cooperate();

    // NOTE(rtk0c): static nested class is essentially the same as a separate file, but allows us to avoid having 4 more
    //              tabs open, each with bare any code inside

    public static class Cooperate extends Strategy {
        @Override
        public boolean cooperate() {
            return true;
        }
    }

    public static class Cheat extends Strategy {
        @Override
        public boolean cooperate() {
            return false;
        }
    }

    public static class RandomlyCooperate extends Strategy {
        @Override
        public boolean cooperate() {
            return Utilities.rng.nextDouble() < 0.5;
        }
    }

    public static class Tit4Tat extends Strategy {
        @Override
        public boolean cooperate() {
            return !myPrisoner.hasPartnerCheated();
        }
    }
}
