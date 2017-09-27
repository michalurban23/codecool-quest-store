package com.codecool.rmbk.dao;

import java.sql.*;
import java.util.*;

public class SqlDAO {

    private final String databaseURL = "jdbc:sqlite::resource:queststore.db";
    private final String driver = "org.sqlite.JDBC";

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    private ArrayList<ArrayList<String>> results = new ArrayList<>();
    private String status;

    ArrayList<ArrayList<String>> getResults() {

        return this.results;
    }

    private void resetResults() {

        resultSet = null;
        results.clear();
    }

    void handleQuery(String query) {

        try {
            openDB();
            if (query.startsWith("SELECT")) {
                executeQuery(query);
                saveResults();
            } else {
                executeUpdate(query);
            }
            closeDB();
        } catch (SQLException e) {
            terminateConnection(e);
        }
    }

    private void openDB() throws SQLException {

        resetResults();

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(databaseURL);
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            terminateConnection(e);
        }
    }

    private void executeUpdate(String query) throws SQLException {

        statement.executeUpdate(query);
    }

    private void executeQuery(String query) throws SQLException {

        resultSet = statement.executeQuery(query);
    }

    private void closeDB() throws SQLException {

        statement.close();
        connection.close();
    }

    private void terminateConnection(Exception e) {

        this.status = "Query failure. SQL message:\n" + e.getMessage();
        // plus something else if we need
    }

    private void saveResults() throws SQLException {

        int columnsAmount = resultSet.getMetaData().getColumnCount();
        int index = 1;
        results = new ArrayList<>();

        // getColumnsInfo(columnsAmount);

        while (resultSet.next()) {

            ArrayList<String> row = new ArrayList<>();

            for (int column=1; column <= columnsAmount; column++) {
                row.add(resultSet.getString(column));
            }
            results.add(row);
        }
    }

    public ArrayList<ArrayList<String>> processQuery(String query) {

        handleQuery(query);
        results = getResults();

        return results;
    }

    // private void getColumnsInfo(int columns) throws SQLException {
    //
    //     resultsInfo = new ArrayList<>();
    //
    //     for (int i=1; i<=columns; i++) {
    //         String label = resultSet.getMetaData().getColumnLabel(i);
    //         resultsInfo.add(label);
    //     }
    //     results.add(resultsInfo);
    // }

}
