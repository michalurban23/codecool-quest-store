package com.codecool.rmbk.dao;

import java.util.ArrayList;
import java.util.List;

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

        String query = String.format("SELECT users.id, first_name, last_name, email, address, status FROM users JOIN login_info ON login_info.id == users.id WHERE login ='%s';", login);
        ArrayList<ArrayList<String>> rw = processQuery(query);
        return getUserFromArray(rw.get(1).get(5), rw.get(1).subList(0, 5));
    }

    private User getUserFromArray(String type, List<String> array) {

        User loged = null;
        if (type.equals("Mentor")) {
            loged =  new Mentor(array.toArray(new String[array.size()]));
        }
        else if(type.equals("Student")) {
            loged =  new Student(array.toArray(new String[array.size()]));
        }
        else if(type.equals("Admin")) {
            loged = new Admin(array.toArray(new String[array.size()]));
        }
        return loged;
    }

    @Override
    public String getUserTypeByLogin(String login) {

        String query = String.format("SELECT status FROM users JOIN login_info ON login_info.id == users.id WHERE login ='%s';", login);
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

    public ArrayList<ArrayList<String>> getIdNameList(String userType) {

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
        return handleQuery(query);
    }

    public Boolean updateUserSurname(User user, String surname) {

        String query = String.format("UPDATE users SET last_name = '%s' WHERE id = %d;", surname, user.getID());
        return handleQuery(query);
    }

    public Boolean updateUserEmail(User user, String email) {

        String query = String.format("UPDATE users SET email = '%s' WHERE id = %d;", email, user.getID());
        return handleQuery(query);
    }

    public Boolean updateUserAddress(User user, String address) {

        String query = String.format("UPDATE users SET address = '%s' WHERE id = %d;", address, user.getID());
        return handleQuery(query);
    }

    @Override
    public Boolean updateUser(User user) {

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
        query = String.format("SELECT id FROM users WHERE first_name IS NULL;");
        ArrayList<ArrayList<String>> queryResult = processQuery(query);
        return getUserByID(Integer.parseInt(queryResult.get(1).get(0)));
    }

}

