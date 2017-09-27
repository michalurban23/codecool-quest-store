package com.codecool.rmbk.dao;

import java.util.ArrayList;

public class SQLUsers extends SqlDAO implements UserInfoDAO {

    public void getAllUsers() {
        String query = "SELECT * FROM users";

        processQuery(query);
    }

    public void getUser(int id) {
        String query = "SELECT * from users WHERE id = '" + id + "';";

        processQuery(query);
    }

}

