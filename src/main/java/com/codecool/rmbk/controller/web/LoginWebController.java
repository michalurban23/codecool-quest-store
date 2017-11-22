package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLLoginDAO;
import com.codecool.rmbk.dao.SQLUsers;
import com.codecool.rmbk.model.Session;
import com.codecool.rmbk.model.usr.User;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class LoginWebController extends CommonHandler {

    private String loginUserName;
    private String loginPassword;
    private SQLLoginDAO dataAccess = new SQLLoginDAO();
    private SQLUsers userDao = new SQLUsers();

    public void handle(HttpExchange httpExchange) throws IOException {

        setHttpExchange(httpExchange);

        if (user == null) {
            setupLoginProcess();
        } else {
            send302("/index");
        }
    }

    private void logUserIn() throws IOException {

        String sessionID = cookieHandler.setNewSessionId();
        user = userDao.getUserByLogin(loginUserName);

        session = new Session(sessionID);
        sessionDao.addSession(session, loginUserName);

        cookieHandler.setStatusToLoggedIn();
        send302("/index");
    }

    private void readUserCredentials() {

        try {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(),
                    "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map<String, String> inputs = parseFormData(formData);
            loginUserName = inputs.get("name");
            loginPassword = inputs.get("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupLoginProcess() throws IOException {

        SQLLoginDAO.setPermission();
        String method = httpExchange.getRequestMethod();

        if (method.equals("GET")) {
            response = webDisplay.getLoginScreen(null);
        } else if (method.equals("POST")) {
            readUserCredentials();
            if (dataAccess.login(loginUserName, loginPassword)) {
                logUserIn();
            } else {
                response = webDisplay.getLoginScreen("Invalid username or password!");
            }
        }
        send200(response);
    }

}
