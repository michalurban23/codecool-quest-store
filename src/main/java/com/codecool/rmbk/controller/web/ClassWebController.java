package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.*;
import com.codecool.rmbk.model.usr.*;
import com.codecool.rmbk.view.KlassWebView;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassWebController extends CommonHandler {

    private SQLKlass sqlClass = new SQLKlass();
    private SQLUsers sqlUsers = new SQLUsers();

    private MenuDAO menuDAO = new SQLMenuDAO();

    private Group object;

    private KlassWebView view;

    public void handle(HttpExchange httpExchange) throws IOException {

        view = new KlassWebView();

        setConnectionData(httpExchange);
        parseURIstring(getRequestURI());
        validateRequest();
        setObject();

        view.setHeader(user);
        view.setMainMenu(mainMenu);
        view.setFooter();

        if (parsedURI.get("object") == null) {
            showList();
        } else if (parsedURI.get("action") == null){
            Group object = sqlClass.getGroupById(Integer.parseInt(parsedURI.get("object")));
            showDetails(object);
        } else {
            performAction();
        }
    }

    private void setObject() {
    }

    private void performAction() throws IOException {

        String action = parsedURI.get("action");
        String objectId = parsedURI.get("object");
        Group object = null;
        User subject;

        if (objectId != null && !objectId.equals("new")) {
            object = sqlClass.getGroupById(Integer.parseInt(parsedURI.get("object")));
        }
        switch (action) {
            case "add":
                addClass();
                break;
            case "edit":
                editClassData(object);
                break;
            case "remove":
                sqlClass.removeGroup(object);
                send302(String.format("/%s", parsedURI.get("controller")));
                break;
            case "addStudents":
                addStudents(object);
                break;
            case "removeStudent":
                subject = sqlUsers.getUserByID(Integer.parseInt(parsedURI.get("subject")));
                sqlClass.removeUserFromGroup(object, subject);
                send302(String.format("/%s/%s", parsedURI.get("controller"), objectId));
                break;
            case "assignMentor":
                assignMentor((Klass) object);
                break;
            case "removeMentor":
                subject = sqlUsers.getUserByID(Integer.parseInt(parsedURI.get("subject")));
                sqlClass.removeUserFromGroup(object, subject);
                send302(String.format("/%s/%s", parsedURI.get("controller"), objectId));
                break;
            default:
                send404();
                break;
        }
    }

    private void addStudents(Group object) throws IOException {
        String method = httpExchange.getRequestMethod();
        if (method.equals("GET")) {
            view.setAddUserView(sqlUsers.getUserMap("Student"));
        } else if (method.equals("POST")) {
            Map<String,String> inputs = readInputs();
            for (String url : inputs.keySet()) {
                sqlClass.addUserToGroup(object, sqlUsers.getUserByID(Integer.parseInt(url.split("student/", 2)[1])));
            }
            send302(String.format("/%s/%s", parsedURI.get("controller"), String.valueOf(object.getID())));
        }
    }

    private void assignMentor(Klass object) throws IOException {
        String method = httpExchange.getRequestMethod();
        if (method.equals("GET")) {
            view.setAssignMentorView(sqlUsers.getUserMap("Mentor"));
        } else if (method.equals("POST")) {
            Map<String,String> inputs = readInputs();
            User mentorToAssign = sqlUsers.getUserByID(Integer.parseInt(inputs.get("mentor_id")));
            object.setMentor((Mentor) mentorToAssign);
            sqlClass.addUserToGroup(object, mentorToAssign);
            send302(String.format("/%s/%s", parsedURI.get("controller"), String.valueOf(object.getID())));
        }

    }

    private void addClass() throws IOException {
        String method = httpExchange.getRequestMethod();
        if (method.equals("GET")) {
            view.setAddClassView();
            send200(view.getResponse());
        } else if (method.equals("POST")) {
            Group newGroup = sqlClass.createGroup();
            Map<String,String> inputs = readInputs();
            newGroup.setName(inputs.get("name"));
            sqlClass.renameGroup(newGroup, inputs.get("name"));
            send302(String.format("/%s/%s", parsedURI.get("controller"), String.valueOf(newGroup.getID())));
        }
    }

    private void editClassData(Group object) throws IOException {
        String method = httpExchange.getRequestMethod();
        if (method.equals("GET")) {
            view.setEditClassDataView(object.getFullInfoMap());
            send200(view.getResponse());
        } else if (method.equals("POST")) {
            Map<String,String> inputs = readInputs();
            object.setName(inputs.get("name"));
            sqlClass.renameGroup(object, inputs.get("name"));
            send302(String.format("/%s/%s", parsedURI.get("controller"), String.valueOf(object.getID())));
        }
    }

    private void showDetails(Group object) throws IOException {

        if (user.getClass().getSimpleName().equals("Student")) {
            send403();
        } else {
            view.setContextMenu(prepareContextMenu(getContextOptions()));
            view.setClassDetailsView(object);
            send200(view.getResponse());
        }
    }

    private void showList() throws IOException {

        if (user.getClass().getSimpleName().equals("Student")) {
            send403();
        } else {
            view.setContextMenu(prepareContextMenu(getContextOptions()));
            view.setClassListView(sqlClass.getGroupMap(parsedURI.get("controller")));
            send200(view.getResponse());
        }

    }

    private String[] getContextOptions () {

        List<String> options = new ArrayList<>();

        if (object == null) {
            if (user.getClass().getSimpleName().equals("Admin")) {
                options.add("Add");
            }
        } else {
            switch (user.getClass().getSimpleName()) {
                case "Admin" :
                    options.add("Edit");
                    options.add("Remove");
                    if (sqlClass.getMentorsList(object).isEmpty()) {
                        options.add("Assign mentor");
                    } else {
                        options.add("Remove mentor");
                    }
                    break;
                case "Mentor" :
                    options.add("Add student");
                    break;
            }
        }
        return options.toArray(new String[options.size()]);
    }
}