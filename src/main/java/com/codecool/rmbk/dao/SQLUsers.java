package com.codecool.rmbk.dao;

import java.util.ArrayList;
import java.util.List;

import com.codecool.rmbk.model.usr.*;


public class SQLUsers extends SqlDAO implements UserInfoDAO {

    public void getAllUsers() {

        String query = "SELECT * FROM users;";
        processQuery(query, null);
    }

    public void getUser(int id) {

        String query = "SELECT * FROM users WHERE id = ?;";
        processQuery(query, new String[] {"" + id});
    }

    @Override
    public User getUserByLogin(String login){

        String query = "SELECT users.id, first_name, last_name, email, address, status FROM users JOIN login_info ON login_info.id == users.id WHERE login = ?;";
        ArrayList<ArrayList<String>> rw = processQuery(query, new String[] {login});
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

        String query = "SELECT status FROM users JOIN login_info ON login_info.id == users.id WHERE login = ?;";
        return processQuery(query, new String[] {login}).get(1).get(0);
    }

    public ArrayList<String> getNameList(String userType) {

        String query = "SELECT (first_name || \" \" || last_name) as full_name FROM users WHERE status = ?;";
        ArrayList<String> result = new ArrayList<>();
        ArrayList<ArrayList<String>> queryResult = processQuery(query, new String[] {userType});
        for(int i=1; i<queryResult.size(); i++){
            result.add(queryResult.get(i).get(0));
        }
        return result;
    }

    @Override
    public User getUserByName(String name) {

        String query = "SELECT * FROM users WHERE first_name = ?;";
        ArrayList<ArrayList<String>> result = processQuery(query, new String[] {name});
        String userType = result.get(1).get(5);
        return getUserFromArray(userType, result.get(1));
    }

    @Override
    public User getUserByID(Integer id) {

        String query = "SELECT * FROM users WHERE id = ?;";
        ArrayList<ArrayList<String>> result = processQuery(query, new String[] {"" + id});
        String userType = result.get(1).get(5);
        return getUserFromArray(userType, result.get(1));
    }

    @Override
    public ArrayList<User> getUserList(String userType) {

        String query = "SELECT * FROM users WHERE status = ?;";
        // we can use WordUtils.capitalizeFully(userType) to ensure that it will mach our record in database
        ArrayList<ArrayList<String>> queryResult = processQuery(query, new String[] {userType});
        ArrayList<User> result = new ArrayList<>();
        for(int i=1; i<queryResult.size(); i++){
            result.add(getUserFromArray(userType, queryResult.get(i)));
        }
        return result;
    }

    public ArrayList<ArrayList<String>> getIdNameList(String userType) {

        String query = "SELECT id, (first_name || \" \" || last_name) as full_name FROM users WHERE status = ?;";
        ArrayList<ArrayList<String>> queryResult = processQuery(query, new String[] {userType});
        return new ArrayList<>(queryResult.subList(1, queryResult.size()));
    }


    @Override
    public Boolean removeUser(User user) {

        String query = "DELETE FROM users WHERE id = ?;" +
                       "DELETE FROM user_groups WHERE user_id = ?;";
        return handleQuery(query, new String[] {"" + user.getID(), "" + user.getID()});
    }

    public Boolean updateUserName(User user, String name) {

        String query = "UPDATE users SET first_name = ? WHERE id = ?;";
        return handleQuery(query, new String[] {name, "" + user.getID()});
    }

    public Boolean updateUserSurname(User user, String surname) {

        String query = "UPDATE users SET last_name = ? WHERE id = ?;";
        return handleQuery(query, new String[] {surname, "" + user.getID()});
    }

    public Boolean updateUserEmail(User user, String email) {

        String query = String.format("UPDATE users SET email = ? WHERE id = ?;", email, user.getID());
        return handleQuery(query, new String[] {email, "" + user.getID()});
    }

    public Boolean updateUserAddress(User user, String address) {

        String query = "UPDATE users SET address = ? WHERE id = ?;";
        return handleQuery(query, new String[] {address, "" + user.getID()});
    }

    @Override
    public Boolean updateUser(User user) {

        updateLoginDB(user);

        String query = "UPDATE users SET first_name = ?, last_name = ?, email = ?, " +
                "address = ? WHERE id = ?;";
        return handleQuery(query, new String[] {user.getFirstName(), user.getLastName(), user.getEmail(),
                                                user.getAddress(), "" + user.getID()});
    }

    private void updateLoginDB(User user) {

        String name = user.getLastName();

        String query = "UPDATE login_info SET login = ? WHERE login = 'new_user';";
        String[] data = {name};

        processQuery(query, data);
    }

    @Override
    public User addUser(String userType) {

        addNewLogin();

        String query = "INSERT INTO users (status) values (?);";
        processQuery(query, new String[] {userType});

        query = "SELECT id FROM users WHERE first_name IS NULL;";
        ArrayList<ArrayList<String>> queryResult = processQuery(query, null);
        return getUserByID(Integer.parseInt(queryResult.get(1).get(0)));
    }

    private void addNewLogin() {

        String salt = PasswordHash.getSalt();
        String pass = PasswordHash.hash("pass", salt);

        String loginQuery = "INSERT INTO login_info (password, login, salt) " +
                            "VALUES (?, 'new_user', ?)";
        String[] data = {pass, salt};

        processQuery(loginQuery, data);
    }
}

