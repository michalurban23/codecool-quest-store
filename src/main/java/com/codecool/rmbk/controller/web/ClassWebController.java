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

    private Klass object;

    private KlassWebView view;

    public void handle(HttpExchange httpExchange) throws IOException {

        view = new KlassWebView();

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
            object = sqlClass.getKlassById(Integer.parseInt(parsedURI.get("object")));
        }
    }

    private void performAction() throws IOException {

        String action = parsedURI.get("action");
        User subject;

        switch (action) {
            case "add":
                addClass();
                break;
            case "edit":
                editClassData();
                break;
            case "remove":
                sqlClass.removeGroup(object) ;
                send302(String.format("/%s", parsedURI.get("controller")));
                break;
            case "addStudents":
                addStudents();
                break;
            case "removestudents":
                subject = sqlUsers.getUserByID(Integer.parseInt(parsedURI.get("subject")));
                sqlClass.removeUserFromKlass(object, subject);
                send302(String.format("/%s/%s", parsedURI.get("controller"), object.getID()));
                break;
            case "assignmentor":
                if (parsedURI.get("controller").equals("class")) {
                    assignMentor(object);
                } else {
                    send404();
                }
                break;
            case "removementor":
                subject = sqlUsers.getUserByID(Integer.parseInt(parsedURI.get("subject")));
                sqlClass.removeUserFromKlass(object, subject);
                send302(String.format("/%s/%s", parsedURI.get("controller"), object.getID()));
                break;
            default:
                send404();
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
                sqlClass.addUserToKlass(object, sqlUsers.getUserByID(Integer.parseInt(url.split("student/", 2)[1])));
            }
            send302(String.format("/%s/%s", parsedURI.get("controller"), String.valueOf(object.getID())));
        }
    }

    private void assignMentor(Klass object) throws IOException {

        String method = httpExchange.getRequestMethod();

        if (method.equals("GET")) {
            view.setAssignMentorView(sqlUsers.getUserMap("Mentor"));
            send200(view.getResponse());
        } else if (method.equals("POST")) {
            Map<String,String> inputs = readInputs();
            User mentorToAssign = sqlUsers.getUserByID(Integer.parseInt(inputs.get("mentor_id")));
            object.setMentor((Mentor) mentorToAssign);
            sqlClass.addUserToKlass(object, mentorToAssign);
            send302(String.format("/%s/%s", parsedURI.get("controller"), String.valueOf(object.getID())));
        }

    }

    private void addClass() throws IOException {

        String method = httpExchange.getRequestMethod();

        if (method.equals("GET")) {
            view.setAddClassView();
            send200(view.getResponse());
        } else if (method.equals("POST")) {
            Klass newGroup = sqlClass.createKlass();
            Map<String,String> inputs = readInputs();
            newGroup.setName(inputs.get("name"));
            sqlClass.renameGroup(newGroup, inputs.get("name"));
            send302(String.format("/%s/%s", parsedURI.get("controller"), String.valueOf(newGroup.getID())));
        }
    }

    private void editClassData() throws IOException {

        String method = httpExchange.getRequestMethod();

        if (method.equals("GET")) {
            view.setEditClassDataView(object);
            send200(view.getResponse());

        } else if (method.equals("POST")) {
            Map<String,String> inputs = readInputs();
            object.setName(inputs.get("name"));
            sqlClass.renameGroup(object, inputs.get("name"));
            send302(String.format("/%s/%s", parsedURI.get("controller"), String.valueOf(object.getID())));
        }
    }

    private void showDetails() throws IOException {

        if (loggedUser.getClass().getSimpleName().equals("Student")) {
            send403();
        } else if (loggedUser.getClass().getSimpleName().equals("Mentor")) {
            view.setClassDetailsView(object);
            send200(view.getResponse());
        } else {
            view.setContextMenu(prepareContextMenu(getContextOptions()));
            view.setClassDetailsView(object);
            send200(view.getResponse());
        }
    }

    private void showList() throws IOException {

        if (loggedUser.getClass().getSimpleName().equals("Student")) {
            send403();
        } else if (loggedUser.getClass().getSimpleName().equals("Mentor")) {
            view.setClassListMentorView(sqlClass.getKlassURLMap());
            send200(view.getResponse());
        } else {
            view.setClassListView(sqlClass.getKlassURLMap());
            send200(view.getResponse());
        }

    }

    private String[] getContextOptions () {

        List<String> options = new ArrayList<>();

        if (object == null) {
            if (loggedUser.getClass().getSimpleName().equals("Admin")) {
                options.add("Add");
            }
        } else {
            switch (loggedUser.getClass().getSimpleName()) {
                case "Admin" :
                    options.add("Edit");
                    options.add("Remove");
                    if (object.getMentor() == null) {
                        options.add("AssignMentor");
                    } else {
                        options.add("RemoveMentor");
                    }
                    break;
                case "Mentor" :
                    options.add("AddStudents");
                    break;
            }
        }
        return options.toArray(new String[options.size()]);
    }
}