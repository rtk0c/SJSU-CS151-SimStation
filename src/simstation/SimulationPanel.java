package simstation;

import mvc.AppFactory;
import mvc.AppPanel;

import javax.swing.*;
import java.awt.*;


public class SimulationPanel extends AppPanel {
    public SimulationPanel(AppFactory factory) {
        super(factory);

        controlPanel.setLayout(new GridLayout(5, 1));

        //TODO format panel,
        // give each button own panel positioned NORTH?

        JPanel startPanel = new JPanel();
        startPanel.setBackground(Color.pink);
        startPanel.setLayout(new FlowLayout());
        JButton startButton = new JButton("Start");
        startButton.addActionListener(this);
        startButton.setBackground(Color.pink);
        startPanel.add(startButton, BorderLayout.NORTH);


        JPanel suspendPanel = new JPanel();
        suspendPanel.setBackground(Color.pink);
        suspendPanel.setLayout(new FlowLayout());
        JButton suspendButton = new JButton("Suspend");
        suspendButton.addActionListener(this);
        suspendButton.setBackground(Color.pink);
        suspendPanel.add(suspendButton, BorderLayout.NORTH);

        JPanel resumePanel = new JPanel();
        resumePanel.setBackground(Color.pink);
        resumePanel.setLayout(new FlowLayout());
        JButton resumeButton = new JButton("Resume");
        resumeButton.addActionListener(this);
        resumeButton.setBackground(Color.pink);
        resumePanel.add(resumeButton, BorderLayout.NORTH);

        JPanel stopPanel = new JPanel();
        stopPanel.setBackground(Color.pink);
        stopPanel.setLayout(new FlowLayout());
        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(this);
        stopButton.setBackground(Color.pink);
        stopPanel.add(stopButton, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel();
        statsPanel.setBackground(Color.pink);
        statsPanel.setLayout(new FlowLayout());
        JButton statsButton = new JButton("Stats");
        statsButton.addActionListener(this);
        statsButton.setBackground(Color.pink);
        statsPanel.add(statsButton, BorderLayout.NORTH);

        controlPanel.add(BorderLayout.NORTH, startPanel);
        controlPanel.add(BorderLayout.NORTH, suspendPanel);
        controlPanel.add(BorderLayout.NORTH, resumePanel);
        controlPanel.add(BorderLayout.NORTH, stopPanel);
        controlPanel.add(BorderLayout.NORTH, statsPanel);

    }


    //TODO confirm main exists here or not
    public static void main(String[] args) {
        AppFactory factory = new SimulationFactory();
        AppPanel panel = new SimulationPanel(factory);
        panel.display();
    }
}
