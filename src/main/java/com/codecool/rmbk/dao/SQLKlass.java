package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.*;

import java.util.*;

public class SQLKlass extends SqlDAO {

    public ArrayList<Klass> getKlassList() {

        String query = "SELECT id FROM class_name;";
        ArrayList<Klass> result = new ArrayList<>();

        processQuery(query, null);

        ArrayList<ArrayList<String>> results = (ArrayList) getResults().clone();
        for (int i = 1 ; i < results.size() ; i++) {
            String id = results.get(i).get(0);
            Klass klass = getKlassById(Integer.parseInt(id));
            result.add(klass);
        }
        return result;
    }

    public Klass getKlassById(Integer id) {

        Klass klass = null;
        String query = "SELECT name FROM class_name WHERE id = ?;";
        ArrayList<ArrayList<String>> queryResult = (ArrayList) processQuery(query, new String[]{"" + id}).clone();

        if (queryResult.size() > 1) {
            klass = new Klass(id, queryResult.get(1).get(0));
            klass.setMentor(getMentor(klass));
            klass.setMembers(getStudentsList(klass));
        }
        return klass;
    }

    public Map<String, String> getKlassURLMap() {

        Map<String, String> groupsMap = new HashMap<>();

        for (Klass klass : getKlassList()) {
            groupsMap.put(String.format("/class/%s", String.valueOf(klass.getID())), klass.getName());
        }
        return groupsMap;
    }

    public ArrayList<Student> getStudentsList(Group group) {

        ArrayList<Student> students = new ArrayList<>();

        String query = "SELECT id FROM users WHERE status = 'Student' AND " + "class_name = (SELECT name FROM class_name WHERE id = ?);";
        ArrayList<ArrayList<String>> queryResult = processQuery(query, new String[]{String.valueOf(group.getID())});
        SQLUsers sqlUsers = new SQLUsers();

        for (ArrayList<String> record : queryResult.subList(1, queryResult.size())) {
            User student = sqlUsers.getUserByID(Integer.parseInt(record.get(0)));
            students.add((Student) student);
        }
        return students;
    }

    public Mentor getMentor(Klass group) {

        String query = "SELECT id FROM users WHERE status = 'Mentor' AND " + "class_name = (SELECT name FROM class_name WHERE id = ?);";
        ArrayList<ArrayList<String>> queryResult = processQuery(query, new String[]{String.valueOf(group.getID())});
        SQLUsers sqlUsers = new SQLUsers();

        User mentor = sqlUsers.getUserByID(Integer.parseInt(queryResult.subList(1, queryResult.size()).get(0).get(0)));
        return (Mentor) mentor;
    }

    public Boolean isInGroup(User user, Group group) {

        String query = "SELECT * FROM users WHERE id = ? AND class_name = ?;";
        return processQuery(query, new String[]{"" + user.getID(), group.getName()}).size() > 1;
    }

    public Boolean removeUserFromKlass(Klass group, User student) {

        String query = "UPDATE users SET class_name = null WHERE id = ?;";
        return handleQuery(query, new String[]{"" + student.getID()});
    }

    public void addUserToKlass(Klass group, User user) {

        String query = "UPDATE users SET class_name = ? WHERE id = ?;";
        handleQuery(query, new String[]{group.getName(), "" + user.getID()});
    }

    public Klass createKlass() {

        String query = "INSERT INTO class_name (name) VALUES (null);";
        handleQuery(query, null);

        query = "SELECT * FROM class_name ORDER BY id DESC LIMIT 1;";
        ArrayList<ArrayList<String>> queryResult = processQuery(query, null);

        return getKlassById(Integer.parseInt(queryResult.get(1).get(0)));
    }

    public Boolean removeGroup(Group group) {

        String query = "DELETE FROM class_name WHERE id = ?;";

        return handleQuery(query, new String[]{"" + group.getID()});
    }

    public Boolean renameGroup(Group group, String newName) {

        String query = "UPDATE class_name SET name = ? WHERE id = ?;";

        return handleQuery(query, new String[]{newName, String.valueOf(group.getID())});
    }

}
