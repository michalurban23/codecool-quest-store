package dao;

import java.util.HashMap;

public interface LoginDAO {

    public String getUserByLogin(String login);
    public String getUserTypeByLogin(String login);
    public HashMap<String, String> load();
}
