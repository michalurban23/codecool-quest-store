import view.*;
import java.util.TreeMap;
import java.util.ArrayList;

public class Dupa extends ConsoleView {

    public Dupa() {}

    public TreeMap<String, Integer> createMainMenu() {
        TreeMap<String, Integer> mainMenu = new TreeMap<>();
        mainMenu.put("Edit account data", 1);
        mainMenu.put("Quests", 2);
        mainMenu.put("Artifacts", 3);
        mainMenu.put("Log out", 0);
        return mainMenu;
    }
}
