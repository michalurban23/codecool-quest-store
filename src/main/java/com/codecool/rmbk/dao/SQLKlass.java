package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.*;

import java.util.*;

public class SQLKlass extends SqlDAO {
    
    public ArrayList<Klass> getKlassList() {

        String query = "SELECT id FROM class_name;";
        ArrayList<Group> result = new ArrayList<>();
        ArrayList<ArrayList<String>> queryResult = processQuery(query, null);


        for(ArrayList<String> arr : queryResult.subList(1, queryResult.size())) {
            Klass klass = getKlassById(Integer.parseInt(arr.get(0)));
            klass.setMentor(getMentor(klass));
            klass.setMembers(getStudentsList(klass))
        }
        return result;
    }

    private Klass getKlassById(int i) {
    }


    public Map<String,String> getKlassMap() {

        Map<String,String> groupsMap = new HashMap<>();

        for(Klass klass : getKlassList()) {
            groupsMap.put(String.format("/class/%s", String.valueOf(klass.getID())),
                    klass.getName());
        }
        return groupsMap;
    }
    
    public ArrayList<Student> getStudentsList(Group group) {
        
        ArrayList<Student> students = new ArrayList<>();

        String query = "SELECT id FROM users WHERE status = 'Student' AND " +
                       "class_name = (SELECT name FROM class_name WHERE id = ?);";
        ArrayList<ArrayList<String>> queryResult = processQuery(query, new String[] {String.valueOf(group.getID())});
        SQLUsers sqlUsers = new SQLUsers();
        
        for (ArrayList<String> record : queryResult.subList(1, queryResult.size())) {
            User student = sqlUsers.getUserByID(Integer.parseInt(record.get(0)));
            students.add((Student) student);
        }
        return students;
    }

    public Mentor getMentor(Klass group) {

        String query = "SELECT id FROM users WHERE status = 'Mentor' AND " +
                "class_name = (SELECT name FROM class_name WHERE id = ?);";
        ArrayList<ArrayList<String>> queryResult = processQuery(query, new String[] {String.valueOf(group.getID())});
        SQLUsers sqlUsers = new SQLUsers();

        User mentor = sqlUsers.getUserByID(Integer.parseInt(queryResult.subList(1, queryResult.size()).get(0).get(0)));
        return (Mentor) mentor;
    }

    public Boolean isInGroup(User user, Group group) {

        String query = "SELECT * FROM users WHERE id = ? AND class_name = ?;";
        return processQuery(query, new String[] {"" + user.getID(), group.getName()}).size() > 1;
    }
    
//    public void updateMembers(Group group) {
//
//        String query = "SELECT * FROM users " +
//                "INNER JOIN user_groups ON user_groups.user_id = users.id " +
//                "WHERE user_groups.group_id = ?;";
//        super.updateUsers(group, query);
//    }
    
    public Boolean removeUserFromClass(Klass group, User student) {

        String query = "UPDATE users SET class_name = null WHERE id = ?;";
        return handleQuery(query, new String[] {"" + student.getID()});
    }

    public void addUserToKlass(Klass group, User user) {

        String query = "UPDATE users SET class_name = ? WHERE id = ?;";
        handleQuery(query, new String[] {group.getName(), "" + user.getID()});
    }

    public Map<String,String> getMembersMap (Group group) {

        Map<String,String> usersMap = new LinkedHashMap<>();
        ArrayList<User> members = getStudentsList(group);
        for (User member : members) {
            usersMap.put("/student/" + String.valueOf(member.getID()), member.getFullName());
        }
        return usersMap;
    }


}
