package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.*;

import java.util.ArrayList;

public interface GroupDAO {

    Group createGroup();
    Group getGroupById(Integer id);
    Boolean removeGroup(Group group);
    Boolean renameGroup(Group group, String newName);
    Boolean removeStudentFromGroup(Group group, Student student);
    ArrayList<Group> getGroupList(User user);
    ArrayList<User> getStudentsList(Group group);
    Boolean isInGroup(User user, Group group);
    void updateMembers(Group team);

    void addUserToGroup(Group team, User user);

    ;
}
