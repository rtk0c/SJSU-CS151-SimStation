package mvc;

/*
    Edits:
        Kyle 3/5: Created file
 */
public abstract class Command {
    private Model model;

    public Command(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public abstract void execute() throws Exception;
}
