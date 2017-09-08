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
        String firstName = getUserFirstName(login);
        String lastName = getUserLastName(login);

        if (userType.equals("Student")) {
            return new Student(firstName, lastName);
        } else if (userType.equals("Mentor")) {
            return new Mentor(firstName, lastName);
        }
        return new Admin(firstName, lastName);
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

    private String getUserFirstName(String login) {

        for(String user : this.users) {
            String userLogin = user.split(",")[0];
            String userName = user.split(",")[2];
            if (login.equals(userLogin)) {
                return userName;
            }
        }
        return null;
    }

    private String getUserLastName(String login) {

        for(String user : this.users) {
            String userLogin = user.split(",")[0];
            String userName = user.split(",")[3];
            if (login.equals(userLogin)) {
                return userName;
            }
        }
        return null;
    }

    private void load() {

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
