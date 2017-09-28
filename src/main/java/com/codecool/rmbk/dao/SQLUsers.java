package com.codecool.rmbk.dao;

import java.util.ArrayList;
import com.codecool.rmbk.model.usr.*;


public class SQLUsers extends SqlDAO implements UserInfoDAO {

    private ArrayList<ArrayList<String>> results;

    public void getAllUsers() {

        String query = "SELECT * FROM users";
        processQuery(query);
    }

    public void getUser(int id) {

        String query = "SELECT * FROM users WHERE id = '" + id + "';";
        processQuery(query);
    }

    @Override
    public User getUserByLogin(String login){

        String query = String.format("SELECT * FROM users WHERE login ='%s';", login);
        ArrayList<ArrayList<String>> result = processQuery(query);
        return getUserFromArray(getUserTypeByLogin(login), result.get(1));

    }

    private User getUserFromArray(String type, ArrayList<String> array){
        if(type.equals("Mentor")) return new Mentor(array.toArray(new String[array.size()]));
        else if(type.equals("Student")) return new Student(array.toArray(new String[array.size()]));
        else if(type.equals("Admin")) return new Admin(array.toArray(new String[array.size()]));
        else return null;
    }

    @Override
    public String getUserTypeByLogin(String login) {
        String query = String.format("SELECT status FROM users WHERE login ='%s';", login);
        return processQuery(query).get(1).get(0);
    }

    public ArrayList<String> getNameList(String userType) {
        String query = String.format("SELECT (first_name || \" \" || last_name) as full_name FROM users " +
                "WHERE status = %s;", userType);
        ArrayList<String> result = new ArrayList<>();
        ArrayList<ArrayList<String>> queryResult = processQuery(query);
        for(int i=1; i<queryResult.size(); i++){
            result.add(queryResult.get(i).get(0));
        }
        return result;
    }

    @Override
    public User getUserByName(String name) {

        String query = String.format("SELECT * FROM users WHERE first_name ='%s';", name);
        ArrayList<ArrayList<String>> result = processQuery(query);
        String userType = result.get(1).get(5);
        return getUserFromArray(userType, result.get(1));
    }

    @Override
    public User getUserByID(Integer id) {

        String query = String.format("SELECT * FROM users WHERE id ='%d';", id);
        ArrayList<ArrayList<String>> result = processQuery(query);
        String userType = result.get(1).get(5);
        return getUserFromArray(userType, result.get(1));
    }

    @Override
    public ArrayList<User> getUserList(String userType) {

        String query = String.format("SELECT * FROM users WHERE status ='%s';", userType);
        // we can use WordUtils.capitalizeFully(userType) to ensure that it will mach our record in database
        ArrayList<ArrayList<String>> queryResult = processQuery(query);
        ArrayList<User> result = new ArrayList<>();
        for(int i=1; i<queryResult.size(); i++){
            result.add(getUserFromArray(userType, queryResult.get(i)));
        }
        return result;
    }

    public ArrayList<ArrayList<String>> getIdNameList(String userType){

        String query = String.format("SELECT id, (first_name || \" \" || last_name) as full_name FROM users " +
                "WHERE status = '%s';", userType);
        return processQuery(query);
    }


    @Override
    public Boolean removeUser(User user) {

        String query = String.format("DELETE FROM users WHERE id = %d;", user.getID());
        return handleQuery(query);
    }

    public Boolean updateUserName(User user, String name) {

        String query = String.format("UPDATE users SET first_name = '%s' WHERE id = %d;", name, user.getID());
        return null;
    }

    public Boolean updateUserSurname(User user, String surname) {

        String query = String.format("UPDATE users SET last_name = '%s' WHERE id = %d;", surname, user.getID());
        return null;
    }

    public Boolean updateUserEmail(User user, String email) {

        String query = String.format("UPDATE users SET email = '%s' WHERE id = %d;", email, user.getID());
        return null;
    }

    public Boolean updateUserAddress(User user, String address) {

        String query = String.format("UPDATE users SET address = '%s' WHERE id = %d;", address, user.getID());
        return null;
    }

    @Override
    public Boolean updateUser(User user){
        System.out.println(user);
        System.out.println(user.getID());
        String query = String.format("UPDATE users SET first_name = '%s', last_name = '%s', email = '%s', " +
                "address = '%s' WHERE id = %d;", user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getAddress(), user.getID());
        return handleQuery(query);
    }

    @Override
    public User addUser(String userType) {

        String query = String.format("INSERT INTO users (status) values ('%s');", userType);
        handleQuery(query);
        ArrayList<ArrayList<String>> queryResult = processQuery("SELECT id FORM users WHERE first_name = 'Not Available';");
        System.out.println(queryResult.get(1));
        int newUsersID = Integer.parseInt(queryResult.get(1).get(0));
        if(userType.equals("Mentor")) return new Mentor();
        else if(userType.equals("Admin")) return new Admin();
        else return new Student();
    }

}

