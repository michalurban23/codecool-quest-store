package dao;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import model.usr.*;

public class CSVUserInfoDAO {

    private String filepath;
    private ArrayList<String> users;

    public CSVUserInfoDAO() {

        filepath = "./src/data/user_info.csv";
        users = new ArrayList<>();
        this.load();
    }

    public User getUserByLogin(String login) {

        String userType = getUserTypeByLogin(login);

        if (userType.equals("Student")) {
            return new Student();
        } else if (userType.equals("Mentor")) {
            return new Mentor();
        }
        return new Admin();
    }

    public String getUserTypeByLogin(String login) {

        for(String user : this.users) {
            String userLogin = user.split(",")[0];
            String userStatus = user.split(",")[1];
            if (login.equals(userLogin)) {
                return userStatus;
            }
        }
        return null;
    }

    private void load() {

        String login;
        String userType;

        try(Scanner reader = new Scanner(new BufferedReader(new FileReader(filepath)))) {
            while(reader.hasNext()) {
                String data = reader.nextLine();
                this.users.add(data);
            }
        } catch (IOException e) {
            System.out.println("Could not locate csv files.");
            System.exit(0);
        }
    }
}
