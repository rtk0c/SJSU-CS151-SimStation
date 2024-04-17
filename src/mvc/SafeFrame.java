package mvc;

/*
    Edits:
        Kyle 3/5: Created file, pasted in given code
 */

import java.awt.event.*;
import javax.swing.*;

public class SafeFrame extends JFrame {
    protected void processWindowEvent(WindowEvent ev) {
        super.processWindowEvent(ev);
        if (ev.getID() == WindowEvent.WINDOW_CLOSING) {
            // Clicking X in title bar and clicking Quit menu item should do the same time
            // NOTE(rtk0c): I believe in most Human Interface Design guidelines, title bar should prompt unsaved changes, and Quit should not,
            //              but I've gotten deducted points for doing that!
            app.actionPerformed(new ActionEvent(this, 0, "Quit"));
        }
    }

    private AppPanel app;

    public SafeFrame(AppPanel app) {
        this.app = app;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
}
