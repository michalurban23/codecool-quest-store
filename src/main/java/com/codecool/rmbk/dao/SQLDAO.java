package com.codecool.rmbk.dao;

import java.sql.*;

public class SQLDAO {

    private Connection conn;
    private Statement stmt;

    public void connect() {
        String url = "jdbc:sqlite:queststore.db";
        String driver = "org.sqlite.JDBC";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getData(String query) {
        connect();
        stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);

        stmt.close();
        conn.close();

        return result;
    }

    public void updateData(String query) {
        connect();
        stmt = conn.createStatement();
        stmt.executeUpdate(query);

        stmt.close();
        conn.close();

    }

}
