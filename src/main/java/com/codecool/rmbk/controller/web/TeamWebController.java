package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.*;
import com.codecool.rmbk.model.usr.*;
import com.codecool.rmbk.view.TeamWebView;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TeamWebController extends CommonHandler {

    private SQLTeam sqlTeam = new SQLTeam();
    private SQLUsers sqlUsers = new SQLUsers();

    private Team object;

    private TeamWebView view;

    public void handle(HttpExchange httpExchange) throws IOException {

        view = new TeamWebView();

        setConnectionData(httpExchange);
        parseURIstring(getRequestURI());
        validateRequest();
        setObject();

        view.setHeader(loggedUser);
        view.setMainMenu(mainMenu);
        view.setFooter();

        if (parsedURI.get("object") == null) {
            showList();
        } else if (parsedURI.get("action") == null){
            showDetails();
        } else {
            performAction();
        }
    }

    private void setObject() {

        String objectId = parsedURI.get("object");

        if (objectId != null && !objectId.equals("new")) {
            object = sqlTeam.getTeamById(Integer.parseInt(parsedURI.get("object")));
        }
    }

    private void performAction() throws IOException {
        String action = parsedURI.get("action");
        Student subject;

        switch (action) {
            case "add":
                addTeam();
                break;
            case "edit":
                editClassData();
                break;
            case "remove":
                sqlTeam.removeTeam(object) ;
                send302(String.format("/%s", parsedURI.get("controller")));
                break;
            case "addStudents":
                addStudents();
                break;
            case "removeStudents":
                subject = (Student) sqlUsers.getUserByID(Integer.parseInt(parsedURI.get("subject")));
                sqlTeam.removeUserFromTeam(object, subject);
                send302(String.format("/%s/%s", parsedURI.get("controller"), object.getID()));
                break;
        }
    }

    private void addStudents() throws IOException {
        String method = httpExchange.getRequestMethod();
        if (method.equals("GET")) {
            view.setAddUserView(sqlUsers.getUserMap("Student"));
            send200(view.getResponse());
        } else if (method.equals("POST")) {
            Map<String,String> inputs = readInputs();
            for (String url : inputs.keySet()) {
                sqlTeam.addStudentToTeam(object, (Student) sqlUsers.getUserByID(Integer.parseInt(url.split("student/", 2)[1])));
            }
            send302(String.format("/%s/%s", parsedURI.get("controller"), String.valueOf(object.getID())));
        }
    }

    private void addTeam() throws IOException {
        String method = httpExchange.getRequestMethod();
        if (method.equals("GET")) {
            view.setAddTeamView();
            send200(view.getResponse());
        } else if (method.equals("POST")) {
            Team newGroup = sqlTeam.createTeam();
            Map<String,String> inputs = readInputs();
            newGroup.setName(inputs.get("name"));
            sqlTeam.renameTeam(newGroup, inputs.get("name"));
            send302(String.format("/%s/%s", parsedURI.get("controller"), String.valueOf(newGroup.getID())));
        }
    }

    private void editClassData() throws IOException {
        String method = httpExchange.getRequestMethod();
        if (method.equals("GET")) {
            view.setEditTeamDataView(object);
            send200(view.getResponse());
        } else if (method.equals("POST")) {
            Map<String,String> inputs = readInputs();
            object.setName(inputs.get("name"));
            sqlTeam.renameTeam(object, inputs.get("name"));
            send302(String.format("/%s/%s", parsedURI.get("controller"), String.valueOf(object.getID())));
        }
    }

    private void showDetails() throws IOException {

        if (loggedUser.getClass().getSimpleName().equals("Student")) {
            view.setContextMenu(null);
        } else {
            view.setContextMenu(prepareContextMenu(getContextOptions()));
        }
        view.setTeamDetailsView(object);
        send200(view.getResponse());
    }

    private void showList() throws IOException {
        if (loggedUser.getClass().getSimpleName().equals("Student")) {
            view.setContextMenu(null);
            view.setTeamListStudentView(sqlTeam.getMyTeams((Student) loggedUser));
        } else {
            view.setContextMenu(prepareContextMenu(getContextOptions()));
            view.setTeamListView(sqlTeam.getAllTeams());
        }
        send200(view.getResponse());

    }

    private String[] getContextOptions () {

        List<String> options = new ArrayList<>();

        if (object == null) {
            if (loggedUser.getClass().getSimpleName().equals("Mentor")) {
                options.add("Add");
            }
        } else {
            options.add("AddStudents");
            options.add("Edit");
            options.add("RemoveStudents");
        }
        return options.toArray(new String[options.size()]);
    }
}