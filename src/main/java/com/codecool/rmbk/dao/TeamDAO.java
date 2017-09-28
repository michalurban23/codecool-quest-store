package com.codecool.rmbk.dao;

import com.codecool.rmbk.model.usr.Group;
import com.codecool.rmbk.model.usr.Team;
import com.codecool.rmbk.model.usr.User;

public interface TeamDAO {

    public Team createGroup();
    public Boolean deleteGroup(Team group);
    public Boolean renameGroup(Team group, String newName);
    public Boolean addStudentToGroup(Team group, User student);
    public Boolean removeStudentFromGroup(Team group, User student);

}
