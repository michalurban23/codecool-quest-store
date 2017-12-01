package com.codecool.rmbk.view;

import com.codecool.rmbk.model.usr.Group;
import com.codecool.rmbk.model.usr.Team;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class TeamWebView extends WebView {

    public void setTeamDetailsView (Team object) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/team.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("team_name", object.getName());
        model.with("data", object.getMembersURIMap());
        mainContent = (template.render(model));
    }

    public void setTeamListView(ArrayList<Team> teamlist) {

        Map<String, String> teams = new LinkedHashMap<>();
        for (Team team :teamlist) {
            teams.put(team.getURI(), team.getName());
        }

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/list_content.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("data", teams);
        mainContent = (template.render(model));
    }

    public void setTeamListStudentView(ArrayList<Team> teamlist) {

        Map<String, String> teams = new LinkedHashMap<>();
        for (Team team :teamlist) {
            teams.put(team.getURI(), team.getName());
        }

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/list.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("data", teams);
        mainContent = (template.render(model));
    }

    public void setAddUserView (Map<String,String> usersMap) {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/buyable.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("title", "Choose students to add");
        model.with("data", usersMap);
        mainContent = (template.render(model));
    }

    public void setAddTeamView () {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/add.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("title", "Add new team");
        model.with("data", Group.getFieldLabels());
        mainContent = (template.render(model));
    }

    public void setEditTeamDataView (Team object) {

        Map<String,String> infoMap = new LinkedHashMap<>();
        infoMap.put("name", object.getName());

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/edit.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("title", "Edit team info");
        model.with("data", infoMap);
        mainContent = (template.render(model));
    }
}