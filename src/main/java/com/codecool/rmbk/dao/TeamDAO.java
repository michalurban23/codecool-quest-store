package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.Group;
import com.codecool.rmbk.model.usr.Student;
import com.codecool.rmbk.model.usr.Team;
import com.codecool.rmbk.model.usr.User;

import java.util.ArrayList;

public interface TeamDAO {

    public Team createGroup();
    public Team getTeamById(Integer id);
    public Boolean removeTeam(Team group);
    public Boolean renameGroup(Team group, String newName);
    public Boolean addStudentToGroup(Team group, User student);
    public Boolean removeStudentFromGroup(Team group, User student);
    public ArrayList<Team> getTeamList(User user);
    public ArrayList<Student> getStudentsList(Team group);


}
