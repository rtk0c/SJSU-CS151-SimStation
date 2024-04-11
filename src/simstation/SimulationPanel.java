package simstation;

import mvc.AppFactory;
import mvc.AppPanel;

import javax.swing.*;


public class SimulationPanel extends AppPanel {
    public SimulationPanel(AppFactory factory) {
        super(factory);

        //TODO format panel,
        // give each button own panel positioned NORTH?

        JButton startButton = new JButton("Start");
        controlPanel.add(startButton);
        startButton.addActionListener(this);

        JButton suspendButton = new JButton("Suspend");
        controlPanel.add(suspendButton);
        suspendButton.addActionListener(this);

        JButton resumeButton = new JButton("Resume");
        controlPanel.add(resumeButton);
        resumeButton.addActionListener(this);

        JButton stopButton = new JButton("Stop");
        controlPanel.add(stopButton);
        stopButton.addActionListener(this);

        JButton statsButton = new JButton("Stats");
        controlPanel.add(statsButton);
        statsButton.addActionListener(this);

    }


    //TODO confirm main exists here or not
    public static void main(String[] args) {
        AppFactory factory = new SimStationFactory();
        AppPanel panel = new SimulationPanel(factory);
        panel.display();
    }
}
