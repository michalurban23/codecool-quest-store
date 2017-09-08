package dao;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class CSVLoginDAO implements LoginDAO {

    String filepath = "./src/data/logins.csv";

    public HashMap<String, String> load() {

        String login;
        String password;
        HashMap<String, String> logins = new HashMap<>();

        try(Scanner reader = new Scanner(new BufferedReader(new FileReader(filepath)))) {
            while(reader.hasNext()) {
                String[] data = reader.nextLine().split(",");
                login = data[0];
                password = data[1];
                logins.put(login, password);
            }
        } catch (IOException e) {
            System.err.println("Could not locate csv files.");
            System.exit(0);
        }
        return logins;
    }

}
