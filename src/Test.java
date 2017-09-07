import view.*;
import java.util.TreeMap;
import java.util.ArrayList;


public class Test {

    public static void main(String[] args) {
        Dupa test = new Dupa();
        TreeMap<String, Integer> menu = test.createMainMenu();
        test.showMenu(new ArrayList<String>(menu.keySet()));
    }
}
