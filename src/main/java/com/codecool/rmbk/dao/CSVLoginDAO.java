package com.codecool.rmbk.dao;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class CSVLoginDAO implements LoginDAO {

    String filepath = "./logins.csv";

    public HashMap<String, String> load() {

        String login;
        String password;
        HashMap<String, String> logins = new HashMap<>();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filepath).getFile());

        try(Scanner reader = new Scanner(file)) {
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
