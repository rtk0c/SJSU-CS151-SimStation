package mvc;

/*
    Edits:
        Kyle 3/5: Created file
 */
public interface AppFactory {
    public Model makeModel();

    public View makeView(Model model);

    public String getTitle();

    public String[] getHelp();

    public String about();

    public String[] getEditCommands();

    public Command makeEditCommand(Model model, String type);
}
