package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.User;

import java.util.ArrayList;
import java.util.TreeMap;

public class SQLMenuDAO extends SqlDAO implements MenuDAO {

    public TreeMap<Integer,String> getMenu (String query) {

        TreeMap<Integer,String> MenuMap = new TreeMap<>();
        processQuery(query);

        for (ArrayList<String> row : getResults()) {
            try {
                MenuMap.put(Integer.parseInt(row.get(0)), row.get(1));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return MenuMap;
    }

    @Override
    public TreeMap<Integer, String> getMainMenu(User user) {
        String query = String.format("SELECT id, option FROM main_menu WHERE %1$s_access == 1 ORDER BY id;",
                                     user.getClass().getSimpleName().toLowerCase());
        return getMenu((query));
    }

    @Override
    public TreeMap<Integer, String> getBrowseMenu() {
        String query = "SELECT id, option FROM browse_menu ORDER BY id;";
        return getMenu(query);
    }

    @Override
    public TreeMap<Integer, String> getDetailsMenu() {
        String query = "SELECT id, option FROM details_menu ORDER BY id;";
        return getMenu(query);
    }
}
