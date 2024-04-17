package mvc;

import java.io.*;

/*
    Edits:
        Kyle 3/5: Created file
 */
public class Model extends Publisher implements Serializable {
    public boolean unsavedChanges = false;
    public String fileName;

    // TODO(rtk0c): "We can also call world.changed(name, oldPoint, newPoint) where oldPoint is the former location of the agent and newPoint is the new location"
    //              change this to accept extra params?
    public void changed() {
        unsavedChanges = true;
        notifySubscribers();
    }

    public void setUnsavedChanges(boolean b) {
        unsavedChanges = b;
        notifySubscribers();
    }

    public boolean getUnsavedChanges() {
        return unsavedChanges;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String s) {
        fileName = s;
    }

}
