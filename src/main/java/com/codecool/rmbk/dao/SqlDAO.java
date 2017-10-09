package com.codecool.rmbk.dao;

import com.sun.org.apache.xpath.internal.operations.Bool;

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
    private String status;

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
                isSuccessful = executeUpdate(query);
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

    private boolean executeUpdate(String query) throws SQLException {

        return statement.execute();
    }

    private void executeQuery(String query) throws SQLException {

        resultSet = statement.executeQuery(query);
    }

    private void buildQuery(PreparedStatement statement, String[] stringSet) throws SQLException {

        if(stringSet != null) {
            for(int i=1; i<=stringSet.length; i++) {
                statement.setString(i, stringSet[i-1]);
            }
        }
        System.out.println(statement);
    }

    private void closeDB() throws SQLException {

        statement.close();
        connection.close();
    }

    private void terminateConnection(Exception e) {

        this.status = "Query failure. SQL message:\n" + e.getMessage();
        // plus something else if we need like:
        System.err.println(e.getMessage());
    }

    private void saveResults() throws SQLException {

        int columnsAmount = resultSet.getMetaData().getColumnCount();
        int index = 1;
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
