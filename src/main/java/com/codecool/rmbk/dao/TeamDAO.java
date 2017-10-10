package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.Group;
import com.codecool.rmbk.model.usr.Student;
import com.codecool.rmbk.model.usr.Team;
import com.codecool.rmbk.model.usr.User;

import java.util.ArrayList;

public interface TeamDAO {

    public Group createGroup();
    public Group getTeamById(Integer id);
    public Boolean removeTeam(Group group);
    public Boolean renameGroup(Group group, String newName);
    public Boolean addStudentToGroup(Group group, Student student);
    public Boolean removeStudentFromGroup(Group group, Student student);
    public ArrayList<Team> getTeamList(User user);
    public ArrayList<Student> getStudentsList(Group group);
    public Boolean isInGroup(Student user, Group group);
    void updateMembers(Group team);
}
