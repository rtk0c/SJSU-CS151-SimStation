package mvc;

import java.io.Serializable;

/*
    Edits:
        Kyle 3/5: Created file
 */
public class Model extends Publisher implements Serializable {
    public boolean unsavedChanges = false;
    public String fileName;

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
