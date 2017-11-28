package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.Group;
import com.codecool.rmbk.model.usr.User;

import java.sql.*;
import java.util.*;

public class SqlDAO {

    private final String databaseURL = "jdbc:sqlite:src/main/resources/queststore.db";
    private final String driver = "org.sqlite.JDBC";

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    private ArrayList<ArrayList<String>> results = new ArrayList<>();
    private ArrayList<String> resultsInfo;

    public ArrayList<ArrayList<String>> getResults() {

        return this.results;
    }

    private void resetResults() {

        resultSet = null;
        results.clear();
    }

    boolean handleQuery(String query, String[] stringSet) {

        Boolean isSuccessful = null;

        try {
            openDB();
            statement = connection.prepareStatement(query);
            buildQuery(statement, stringSet);

            if (query.startsWith("SELECT")) {
                resultSet = statement.executeQuery();
                saveResults();
                isSuccessful = results.size() > 1;
            } else {
                isSuccessful = executeUpdate() == 1;
            }
            closeDB();
        } catch (SQLException e) {
            terminateConnection(e);
        }
        return isSuccessful;
    }

    private void openDB() throws SQLException {

        resetResults();

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(databaseURL);
        } catch (ClassNotFoundException e) {
            terminateConnection(e);
        }
    }

    private int executeUpdate() throws SQLException {

        return statement.executeUpdate();
    }

    private void buildQuery(PreparedStatement statement, String[] stringSet) throws SQLException {

        if (stringSet != null) {

            for (int i=1; i<=stringSet.length; i++) {
                statement.setString(i, stringSet[i-1]);
            }
        }
    }

    private void closeDB() throws SQLException {

        statement.close();
        connection.close();
    }

    private void terminateConnection(Exception e) {

        String message = "Query failure. SQL message:\n" + e.getMessage();
        System.out.println(message);
    }

    private void saveResults() throws SQLException {

        int columnsAmount = resultSet.getMetaData().getColumnCount();
        results = new ArrayList<>();

        getColumnsInfo(columnsAmount);

        while (resultSet.next()) {

            ArrayList<String> row = new ArrayList<>();

            for (int column=1; column <= columnsAmount; column++) {
                row.add(resultSet.getString(column));
            }
            results.add(row);
        }
    }

    public ArrayList<ArrayList<String>> processQuery(String query, String[] stringSet) {

        handleQuery(query, stringSet);
        results = getResults();

        return results;
    }

     private void getColumnsInfo(int columns) throws SQLException {

         resultsInfo = new ArrayList<>();

         for (int i=1; i<=columns; i++) {
             String label = resultSet.getMetaData().getColumnLabel(i);
             resultsInfo.add(label);
         }
         results.add(resultsInfo);
     }

}
