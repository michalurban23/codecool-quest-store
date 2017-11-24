package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLUsers;
import com.codecool.rmbk.model.usr.User;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.util.*;
import java.util.Map;

public class UserController extends CommonHandler {

    private SQLUsers userDAO = new SQLUsers();
    private SQLUsers sqlUsers = new SQLUsers();
    private String response;

    public void handle(HttpExchange httpExchange) throws IOException {

        setConnectionData(httpExchange);
        parseURIstring(getRequestURI());
        validateRequest();

        if (parsedURI.get("object") == null) {
            showList();
        } else if (parsedURI.get("action") == null){
            User object = userDAO.getUserByID(Integer.parseInt(parsedURI.get("object")));
            showDetails(object);
        } else {
            performAction();
        }
    }

    private void performAction() throws IOException {
        String action = parsedURI.get("action");
        User object;
        switch (action) {
            case "add":
                addUser();
                break;
            case "edit":
                object = userDAO.getUserByID(Integer.parseInt(parsedURI.get("object")));
                editUserData(object);
                break;
            case "remove":
                object = userDAO.getUserByID(Integer.parseInt(parsedURI.get("object")));
                userDAO.removeUser(object);
                send302(String.format("/%s", parsedURI.get("controller")));
                break;
        }
    }

    private void addUser() throws IOException {
        String method = httpExchange.getRequestMethod();
        if (method.equals("GET")) {
            response = webDisplay.getSiteContent(user.getFullName(),
                                                 mainMenu,
                                                 null,
                                                 String.format("Add new %s", parsedURI.get("controller")),
                                                 User.getFieldLabels(),
                                                 urlAdd);
            send200(response);
        } else if (method.equals("POST")) {
            User newUser = userDAO.addUser(parsedURI.get("controller"));
            Map<String,String> inputs = readInputs();
            newUser.setFirstName(inputs.get("name"));
            newUser.setLastName(inputs.get("surname"));
            newUser.setEmail(inputs.get("email"));
            newUser.setAddress(inputs.get("address"));
            userDAO.updateUser(newUser);
            send302(String.format("/%s/%s", parsedURI.get("controller"), String.valueOf(newUser.getID())));
        }
    }

    private void editUserData(User object) throws IOException {
        String method = httpExchange.getRequestMethod();
        if (method.equals("GET")) {
            response = webDisplay.getSiteContent(user.getFirstName(), mainMenu,
                                                 prepareContextMenu(getAllowedActions()),
                                                 object.getFullInfoMap(),
                                                 urlEdit);
            send200(response);
        } else if (method.equals("POST")) {
            Map<String,String> inputs = readInputs();
            object.setFirstName(inputs.get("name"));
            object.setLastName(inputs.get("surname"));
            object.setEmail(inputs.get("email"));
            object.setAddress(inputs.get("address"));
            userDAO.updateUser(object);
            send302(String.format("/%s/%s", parsedURI.get("controller"), String.valueOf(object.getID())));
        }
    }

    private void showDetails(User object) throws IOException {
        if (isRequestedBySupervisor() || isRequestedBySelf()) {
            response = webDisplay.getSiteContent(user.getFirstName(), mainMenu,
                    prepareContextMenu(getAllowedActions()), object.getFullInfoMap(),
                    urlItem);
            send200(response);
        } else {
            send403();
        }
    }

    private void showList() throws IOException {

        if (isRequestedBySupervisor()) {

            response = webDisplay.getSiteContent(user.getFirstName(), mainMenu,
                    prepareContextMenu(new String[] {"Add"}),
                    sqlUsers.getUserMap(parsedURI.get("controller")), urlList);
            send200(response);
        } else {
            send403();
        }
    }

    private String[] getAllowedActions() {
        List<String> options = new ArrayList<>();
        if (isRequestedBySupervisor()) {
            options.add("Edit");
            options.add("Remove");
        } else if (isRequestedBySelf()) {
            options.add("Edit");
        }
        return options.toArray(new String[options.size()]);
    }

    private boolean isRequestedBySelf() {
        return String.valueOf(user.getID()).equals(parsedURI.get("object"));
    }

    private boolean isRequestedBySupervisor() {
        boolean answer;
        String loggedUserType = user.getClass().getSimpleName();
        String requestedUserType = parsedURI.get("controller");
        if (loggedUserType.equals("Mentor") && requestedUserType.equals("student")) {
            answer = true;
        } else if (loggedUserType.equals("Admin") && requestedUserType.equals("mentor")) {
            answer = true;
        } else {
            answer = false;
        }
        return answer;
    }
}
