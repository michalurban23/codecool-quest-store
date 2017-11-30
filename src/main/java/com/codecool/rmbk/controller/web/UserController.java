package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLLoginDAO;
import com.codecool.rmbk.dao.SQLUsers;
import com.codecool.rmbk.helper.PasswordHash;
import com.codecool.rmbk.model.usr.User;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.util.*;
import java.util.Map;

public class UserController extends CommonHandler {

    private SQLUsers userDAO = new SQLUsers();
    private SQLUsers sqlUsers = new SQLUsers();
    private String response;
    private String accessLevel;

    public void handle(HttpExchange httpExchange) throws IOException {

        setConnectionData(httpExchange);
        parseURIstring(getRequestURI());
        accessLevel = validateRequest();

        String controller = parsedURI.get("controller");
        String object = parsedURI.get("object");
        String action = parsedURI.get("action");

        if (object == null) {
            showList();
        } else if (isObjectInstanceOfController(controller, object)) {
            if (action == null) {
                User user = userDAO.getUserByID(Integer.parseInt(object));
                showDetails(user);
            } else {
                performAction();
            }
        } else {
            send403();
        }
    }

    private void performAction() throws IOException {

        String action = parsedURI.get("action");
        Integer id;
        Boolean editable = isRequestedBySelf() || isRequestedBySupervisor();
        User object;

        if (editable && action.equals("edit")) {
            id = Integer.parseInt(parsedURI.get("object"));
            object = userDAO.getUserByID(id);
            editUserData(object);
        } else if (isRequestedBySelf() && action.equals("logininfo")) {
            editLoginData();
        } else if (isRequestedBySupervisor()) {
            if (action.equals("add")) {
                addUser();
            } else if (action.equals("remove")) {
                id = Integer.parseInt(parsedURI.get("object"));
                object = userDAO.getUserByID(id);
                userDAO.removeUser(object);
                send302(String.format("/%s", parsedURI.get("controller")));
            }
        } else {
            send403();
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

    private void editLoginData() throws IOException {

        String method = httpExchange.getRequestMethod();
        SQLLoginDAO loginDao = new SQLLoginDAO();

        if (method.equals("GET")) {
            response = webDisplay.getSiteContent(user.getFirstName(), mainMenu,
                    prepareContextMenu(getAllowedActions()),
                    loginDao.getCredentialsMap(user.getID()),
                    "templates/edit_login.twig");
        } else if (method.equals("POST")) {

            Map<String,String> inputs = readInputs();
            Map<String, String> credentials = loginDao.getCredentialsMap(user.getID());

            if (isNewPassword(inputs)) {

                if (arePasswordsCompatible(credentials, inputs)){
                    loginDao.updateCredentials(user, inputs);
                    send302("/");
                }
            } else if (isPasswordCorrect(credentials, inputs)) {
                if (isLoginAvailable(inputs.get("login"))) {
                    loginDao.updateLogin(user, inputs);
                    send302("/");
                } else {
                    send302("/static/fail.html");
                }

            } else {
                send302("/static/fail.html");
            }
        }
        send200(response);
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
            options.add("LoginInfo");
        }
        return options.toArray(new String[options.size()]);
    }

    private boolean isRequestedBySelf() {

        return String.valueOf(user.getID()).equals(parsedURI.get("object"));
    }

    private boolean isRequestedBySupervisor() {

        boolean answer;
        String requestedUserType = parsedURI.get("controller");

        if (accessLevel.equals("Mentor") && requestedUserType.equals("student")) {
            answer = true;
        } else if (accessLevel.equals("Admin") && requestedUserType.equals("mentor")) {
            answer = true;
        } else {
            answer = false;
        }
        return answer;
    }

    private boolean isNewPassword(Map<String, String> inputs) {

        boolean isNewPassword;
        if (inputs.get("newpass1").equals("") && inputs.get("newpass2").equals("")) {
            isNewPassword = false;
        } else {
            isNewPassword = true;
        }

        return isNewPassword;
    }

    private boolean isPasswordCorrect(Map<String, String> credentials, Map<String, String> inputs) {

        boolean correct;

        String password = credentials.get("password");
        String passwordInput = PasswordHash.hash(inputs.get("password"), credentials.get("salt"));

        if (password.equals(passwordInput)) {
            correct = true;
        } else {
            correct = false;
        }


        return correct;
    }

    private boolean arePasswordsCompatible(Map<String, String> credentials, Map<String, String> inputs) {

        boolean compatible;
        String oldPassword = credentials.get("password");
        String oldPasswordInput = PasswordHash.hash(inputs.get("password"), credentials.get("salt"));
        String newPassword = inputs.get("newpass1");
        String newPasswordConfirm = inputs.get("newpass2");

        if (oldPassword.equals(oldPasswordInput) && newPassword.equals(newPasswordConfirm)) {
            compatible = true;
        } else {
            compatible = false;
        }
        return compatible;
    }

    private boolean isLoginAvailable(String login) {

        boolean isAvailable;
        List<String> logins = userDAO.getLoginList();

        if (logins.contains(login)) {
            isAvailable = false;
        } else {
            isAvailable = true;
        }

        return isAvailable;
    }

}
