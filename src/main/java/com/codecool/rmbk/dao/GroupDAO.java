package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.Group;
import com.codecool.rmbk.model.usr.Student;
import com.codecool.rmbk.model.usr.Team;
import com.codecool.rmbk.model.usr.User;

import java.util.ArrayList;

public interface GroupDAO {

    Group createGroup();
    Group getGroupById(Integer id);
    Boolean removeGroup(Group group);
    Boolean renameGroup(Group group, String newName);
    Boolean addStudentToGroup(Group group, Student student);
    Boolean removeStudentFromGroup(Group group, Student student);
    ArrayList<Group> getGroupList(User user);
    ArrayList<Student> getStudentsList(Group group);
    Boolean isInGroup(Student user, Group group);
    void updateMembers(Group team);
}
