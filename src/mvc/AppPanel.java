package mvc;

/*
    Edits:
        Kyle 3/5:   Created file, copied in provided AppPanel code and
                    added nested control panel code.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AppPanel extends JPanel implements Subscriber, ActionListener {

    protected Model model;
    protected AppFactory factory;
    protected View view;
    protected JPanel controlPanel;
    private JFrame frame;
    public static int FRAME_WIDTH = 720;
    public static int FRAME_HEIGHT = 540;

    public AppPanel(AppFactory factory) {
        // initialize fields here
        this.factory = factory;
        model = this.factory.makeModel();
        view = this.factory.makeView(model);
        controlPanel = new JPanel();

        this.setLayout((new GridLayout(1, 2)));
        controlPanel.setBackground(Color.pink);
        this.add(controlPanel);
        this.add(view);

        frame = new SafeFrame(this);
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(createMenuBar());
        frame.setTitle(factory.getTitle());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    public void display() {
        frame.setVisible(true);
    }

    public void update() {  /* override in extensions if needed */ }

    public Model getModel() {
        return model;
    }

    // called by file/open and file/new
    public void setModel(Model newModel) {
        this.model.unsubscribe(this);
        this.model = newModel;
        this.model.subscribe(this);
        // view must also unsubscribe then resubscribe:
        view.setModel(this.model);
        model.changed();
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        // add file, edit, and help menus
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Save As", "Open", "Quit"}, this);
        result.add(fileMenu);

        JMenu editMenu = Utilities.makeMenu("Edit", this.factory.getEditCommands(), this);
        result.add(editMenu);

        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);

        return result;
    }

    public void actionPerformed(ActionEvent ae) {
        String cmmd = ae.getActionCommand();
        switch (cmmd) {
            case "Save" -> Utilities.save(model, false);
            case "Save As" -> Utilities.save(model, true);
            case "Open" -> {
                Model newModel = Utilities.open(model);
                if (newModel != null) setModel(newModel);
            }
            case "New" -> {
                if (Utilities.saveChanges(model)) {
                    setModel(factory.makeModel());
                    // needed cuz setModel sets to true:
                    model.setUnsavedChanges(false);
                }
            }
            case "Quit" -> {
                if (Utilities.saveChanges(model)) {
                    System.exit(0);
                }
            }
            case "About" -> Utilities.inform(factory.about());
            case "Help" -> Utilities.inform(factory.getHelp());
            default -> {
                // must be from Edit menu
                Command current = factory.makeEditCommand(model, cmmd);
                if (current == null)
                    break;

                try {
                    current.execute();
                } catch (Exception e) {
                    handleException(e);
                }
            }
        }
    }

    protected void handleException(Exception e) {
        Utilities.error(e);
    }
}
