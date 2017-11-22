package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.dao.SQLUsers;
import com.codecool.rmbk.model.usr.User;
import com.codecool.rmbk.view.WebDisplay;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class UserController extends CommonHandler {

    private SQLUsers userDAO = new SQLUsers();

    public void handle(HttpExchange httpExchange) throws IOException {

        setHttpExchange(httpExchange);
        validateRequest();
        parseURIstring(getRequestURI());

        String controller = user.getClass().getSimpleName().toLowerCase();
        String object = String.valueOf(user.getID());
        send302(String.format("/%1s/%2s", controller, object));
    }


    String[] getAllowedActions() {
        List<String> options = new ArrayList<>();
        boolean accessForSelf = String.valueOf(user.getID()).equals(parsedURI.get("object"));
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
        String requestedUserType = userDAO.getUserByID(Integer.parseInt(parsedURI.get("object"))).getClass().getSimpleName();
        if (loggedUserType.equals("Mentor") && requestedUserType.equals("Student")) {
            answer = true;
        } else if (loggedUserType.equals("Admin") && requestedUserType.equals("Mentor")) {
            answer = true;
        } else {
            answer = false;
        }
        return answer;
    }
}
