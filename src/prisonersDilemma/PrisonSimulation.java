package prisonersDilemma;

import simstation.*;

public class PrisonSimulation extends Simulation {
    @Override
    public void populate() {
        for (int i = 0; i < 10; i++) {
            addPrisoner(i, new Strategy.Cooperate(), "cooperate");
            addPrisoner(i, new Strategy.Cheat(), "cheat");
            addPrisoner(i, new Strategy.RandomlyCooperate(), "randomly_cooperate");
            addPrisoner(i, new Strategy.Tit4Tat(), "tit4tat");
        }
    }

    private void addPrisoner(int i, Strategy strategy, String strategyName) {
        String name = "prisoner" + i + "-" + strategyName;
        addAgent(new Prisoner(name, strategy));
    }

    @Override
    public String stats() {
        // FIXME: this does is """open to extension""" of more strategies
        float fitnessCoop = 0.0f;
        int fitnessCoopCnt = 0;
        float fitnessCheat = 0.0f;
        int fitnessCheatCnt = 0;
        float fitnessRandomCoop = 0.0f;
        int fitnessRandomCoopCnt = 0;
        float fitnessTit4Tat = 0.0f;
        int fitnessTit4TatCnt = 0;

        for (Agent agent : getAgents()) {
            Prisoner prisoner = ((Prisoner) agent);
            Strategy st = prisoner.getStrategy();
            if (st instanceof Strategy.Cooperate) {
                fitnessCoop += prisoner.getFitness();
                fitnessCoopCnt++;
            } else if (st instanceof Strategy.Cheat) {
                fitnessCheat += prisoner.getFitness();
                fitnessCheatCnt++;
            } else if (st instanceof Strategy.RandomlyCooperate) {
                fitnessRandomCoop += prisoner.getFitness();
                fitnessRandomCoopCnt++;
            } else if (st instanceof Strategy.Tit4Tat) {
                fitnessTit4Tat += prisoner.getFitness();
                fitnessTit4TatCnt++;
            }
        }

        fitnessCoop /= fitnessCoopCnt;
        fitnessCheat /= fitnessCheatCnt;
        fitnessRandomCoop /= fitnessRandomCoopCnt;
        fitnessTit4Tat /= fitnessTit4TatCnt;

        return "avg coop fitness = %f\navg cheat fitness = %f\n avg random coop fitness = %f\navg tit4tat fitness = %f"
                .formatted(fitnessCoop, fitnessCheat, fitnessRandomCoop, fitnessTit4Tat);
    }

    public static void main(String[] args) {
        var app = new SimulationPanel(new PrisonFactory());
        app.display();
    }
}
