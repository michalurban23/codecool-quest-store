package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.MenuDAO;
import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.dao.SQLUsers;
import com.codecool.rmbk.dao.UserInfoDAO;
import com.codecool.rmbk.model.usr.User;
import com.codecool.rmbk.view.web.UserWebView;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.util.Map;

public class UserController extends CommonHandler {

    private UserInfoDAO userDAO = new SQLUsers();
    private MenuDAO menuDAO = new SQLMenuDAO();

    private User object;

    private UserWebView view;

    public void handle(HttpExchange httpExchange) throws IOException {

        view = new UserWebView();

        setConnectionData(httpExchange);
        parseURIstring(getRequestURI());
        validateRequest();
        setObject();

        view.setHeader(loggedUser);
        view.setMainMenu(mainMenu);
        view.setFooter();

        if (object == null) {
            showList();
        } else if (parsedURI.get("action") == null){
            showDetails();
        } else {
            performAction();
        }
    }

    private void performAction() throws IOException {
        String action = parsedURI.get("action");
        switch (action) {
            case "add":
                addUser();
                break;
            case "edit":
                editUserData(object);
                break;
            case "remove":
                userDAO.removeUser(object);
                send302(String.format("/%s", parsedURI.get("controller")));
                break;
        }
    }

    private void addUser() throws IOException {
        String method = httpExchange.getRequestMethod();
        if (method.equals("GET")) {
            view.setAddUserView(parsedURI.get("controller"));
            send200(view.getResponse());
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
            view.setEditUserDataView(object.getFullInfoMap());
            send200(view.getResponse());
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

    private void showDetails() throws IOException {
        if (isRequestedBySupervisor() || isRequestedBySelf()) {
            view.setContextMenu(prepareContextMenu(getContextOptions()));
            view.setUserDetailsView(object);
            send200(view.getResponse());
        } else {
            send403();
        }
    }

    private void showList() throws IOException {
        if (isRequestedBySupervisor()) {
            view.setContextMenu(prepareContextMenu(getContextOptions()));
            view.setUsersListView(userDAO.getUserMap(parsedURI.get("controller")));
            send200(view.getResponse());
        } else {
            send403();
        }
    }

    private String[] getContextOptions() {

        String[] options = null;

        if (object == null && isRequestedBySupervisor()) {
            options = new String[] {String.format("Add %s", parsedURI.get("controller"))};
        } else if (isRequestedBySelf()) {
            options = new String[] {"Edit data", "Remove"};
        } else if (isRequestedBySupervisor()) {
            options = new String[] {"Edit data"};
        }
        return options;
    }

    private boolean isRequestedBySelf() {
        return String.valueOf(loggedUser.getID()).equals(parsedURI.get("object"));
    }

    private boolean isRequestedBySupervisor() {
        boolean answer;
        String loggedUserType = loggedUser.getClass().getSimpleName();
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

    private void setObject() {
        if (parsedURI.get("object") != null) {
            object = userDAO.getUserByID(Integer.parseInt(parsedURI.get("object")));
        } else {
            object = null;
        }
    }
}
