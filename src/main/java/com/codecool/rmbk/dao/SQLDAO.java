package com.codecool.rmbk.dao;

import java.sql.*;

public class SQLDAO {

    private Connection conn;
    private Statement stmt;

    public void connect() throws SQLException {

        String url = "jdbc:sqlite::resource:queststore.db";
        String driver = "org.sqlite.JDBC";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getData(String query) {

        try {
            connect();
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            //stmt.close();
            //conn.close();
            return result;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void updateData(String query) {
        try {
            connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(query);

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}
