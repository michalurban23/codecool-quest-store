package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.User;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class SQLMenuDAO extends SqlDAO implements MenuDAO {

    public LinkedHashMap<Integer,String> getMenu (String query) {

        LinkedHashMap<Integer,String> MenuMap = new LinkedHashMap<>();
        processQuery(query);
        ArrayList<ArrayList<String>> queryResult = getResults();
        try {
            for (int i=2; i<queryResult.size(); i++) {
                MenuMap.put(Integer.parseInt(queryResult.get(i).get(0)), queryResult.get(i).get(1));
            }
            MenuMap.put(Integer.parseInt(queryResult.get(1).get(0)), queryResult.get(1).get(1));
            return MenuMap;

        } catch (NumberFormatException e) {
            return null;
        }



    }

    @Override
    public LinkedHashMap<Integer, String> getMainMenu(User user) {
        String query = String.format("SELECT id, option FROM main_menu WHERE %1$s_access == 1 ORDER BY id;",
                                     user.getClass().getSimpleName().toLowerCase());
        return getMenu((query));
    }

    @Override
    public LinkedHashMap<Integer, String> getBrowseMenu() {
        String query = "SELECT id, option FROM browse_menu ORDER BY id;";
        return getMenu(query);
    }

    @Override
    public LinkedHashMap<Integer, String> getDetailsMenu() {
        String query = "SELECT id, option FROM details_menu ORDER BY id;";
        return getMenu(query);
    }
}
