package prisonersDilemma;

import mvc.*;

public abstract class Strategy {
    protected final Prisoner myPrisoner;
    public Strategy(Prisoner myPrisoner) { this.myPrisoner = myPrisoner; }

    public abstract boolean cooperate();

    public enum Type {
        COOPERATE,
        CHEAT,
        RANDOMLY_COOPERATE,
        TIT4TAT,
        ;

        public static final Type[] VALUES = values();
    }

    public static Strategy makeStrategy(Type type, Prisoner forPrisoner) {
        return switch (type) {
            case COOPERATE -> new Cooperate(forPrisoner);
            case CHEAT -> new Cheat(forPrisoner);
            case RANDOMLY_COOPERATE -> new RandomlyCooperate(forPrisoner);
            case TIT4TAT -> new Tit4Tat(forPrisoner);
        };
    }

    public static class Cooperate extends Strategy {
        public Cooperate(Prisoner myPrisoner) { super(myPrisoner); }

        @Override
        public boolean cooperate() {
            return true;
        }
    }

    public static class Cheat extends Strategy {
        public Cheat(Prisoner myPrisoner) { super(myPrisoner); }

        @Override
        public boolean cooperate() {
            return false;
        }
    }

    public static class RandomlyCooperate extends Strategy {
        public RandomlyCooperate(Prisoner myPrisoner) { super(myPrisoner); }

        @Override
        public boolean cooperate() {
            return Utilities.rng.nextDouble() < 0.5;
        }
    }

    public static class Tit4Tat extends Strategy {
        public Tit4Tat(Prisoner myPrisoner) { super(myPrisoner); }

        @Override
        public boolean cooperate() {
            return !myPrisoner.hasPartnerCheated();
        }
    }
}
