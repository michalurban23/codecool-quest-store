package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLClass;
import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.dao.SQLUsers;
import com.codecool.rmbk.model.usr.Group;
import com.codecool.rmbk.model.usr.Klass;
import com.codecool.rmbk.model.usr.Student;
import com.codecool.rmbk.model.usr.User;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassWebController extends CommonHandler {

    private SQLMenuDAO sqlMenuDAO = new SQLMenuDAO();
    private SQLClass sqlClass = new SQLClass();
    private SQLUsers sqlUsers = new SQLUsers();
    String response;

    public void handle(HttpExchange httpExchange) throws IOException {

        setConnectionData(httpExchange);
        parseURIstring(getRequestURI());
        validateRequest();

        if (parsedURI.get("object") == null) {
            showList();
        } else if (parsedURI.get("action") == null){
            Group object = sqlClass.getGroupById(Integer.parseInt(parsedURI.get("object")));
            showDetails(object);
        } else {
            performAction();
        }
    }

    private void performAction() throws IOException {
        String action = parsedURI.get("action");
        String objectId = parsedURI.get("object");
        Group object = null;
        User subject;
        if (objectId != null) {
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
                sqlClass.removeGroup(object) ;
                send302(String.format("/%s", parsedURI.get("controller")));
                break;
            case "addStudents":
//                addStudents(object);
                break;
            case "removeStudent":
                subject = sqlUsers.getUserByID(Integer.parseInt(parsedURI.get("subject")));
                sqlClass.removeStudentFromGroup(object, (Student) subject);
                send302(String.format("/%s/%s", parsedURI.get("controller"), objectId));
                break;
        }
    }

    private void addClass() throws IOException {
        String method = httpExchange.getRequestMethod();
        if (method.equals("GET")) {
            response = webDisplay.getSiteContent(user.getFullName(),
                    sideMenu,
                    null,
                    String.format("Add new %s", parsedURI.get("controller")),
                    Group.getFieldLabels(),
                    urlAdd);
            send200(response);
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
            response = webDisplay.getSiteContent(user.getFullName(),
                    sideMenu,
                    prepareContextMenu(getAllowedDetailsActions(user)),
                    String.format("Rename %s:", object.getClass().getSimpleName()),
                    Group.getFieldLabels(),
                    urlEdit);
            send200(response);
        } else if (method.equals("POST")) {
            Map<String,String> inputs = readInputs();
            object.setName(inputs.get("name"));
            sqlClass.renameGroup(object, inputs.get("name"));
            send302(String.format("/%s/%s", parsedURI.get("controller"), String.valueOf(object.getID())));
        }
    }

    private void showDetails(Group object) throws IOException {

        String userStatus = user.getClass().getSimpleName();

        if (userStatus.equals("Student")) {
            send403();
        } else {
            response = webDisplay.getSiteContent(user.getFullName(), sideMenu,
                    prepareContextMenu(getAllowedDetailsActions(user)), object.getName(),
                    sqlClass.getMembersMap(object),
                    urlItem);
            send200(response);
        }
    }

    private void showList() throws IOException {
        
        String userStatus = user.getClass().getSimpleName();
        List<String> contextMenuOptions = new ArrayList<>();
        System.out.println(userStatus);
        System.out.println(sqlClass.getGroupMap(parsedURI.get("controller")));

        switch (userStatus) {
            case "Admin":
                contextMenuOptions.add("Add");
                response = webDisplay.getSiteContent(user.getFullName(), sideMenu,
                        prepareContextMenu(contextMenuOptions.toArray(new String[contextMenuOptions.size()])),
                        sqlClass.getGroupMap(parsedURI.get("controller")), urlList);
                send200(response);
                break;
            case "Mentor":
                response = webDisplay.getSiteContent(user.getFullName(), sideMenu,
                        prepareContextMenu(contextMenuOptions.toArray(new String[contextMenuOptions.size()])),
                        sqlClass.getGroupMap(parsedURI.get("controller")), urlList);
                send200(response);
                System.out.println(response);
                break;
            default:
                send403();
                break;
        }
    }

    private String[] getAllowedDetailsActions(User user) {

        List<String> options = new ArrayList<>();
        switch (user.getClass().getSimpleName()) {
            case "Admin" :
                options.add("Edit");
                options.add("Remove");
                break;
            case "Mentor" :
                options.add("Add student");
                break;
        }
        return options.toArray(new String[options.size()]);
    }
}