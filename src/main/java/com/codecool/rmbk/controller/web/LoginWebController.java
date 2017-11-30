package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLLoginDAO;
import com.codecool.rmbk.dao.SQLUsers;
import com.codecool.rmbk.model.Session;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class LoginWebController extends CommonHandler {

    private String loginUserName;
    private String loginPassword;
    private Integer loginID;
    private SQLLoginDAO dataAccess = new SQLLoginDAO();
    private SQLUsers userDao = new SQLUsers();

    public void handle(HttpExchange httpExchange) throws IOException {

        setConnectionData(httpExchange);

        Boolean alreadyLogged = Session.sessionExists(cookieHandler.getSessionId());

        if (alreadyLogged) {
            send302("/");
        } else {
            setupLoginProcess();
        }
    }

    private void logUserIn() throws IOException {

        String sessionID = cookieHandler.setNewSessionId();
        loggedUser = userDao.getUserByID(loginID);

        session = Session.addSession(sessionID, loggedUser);
        sessionDao.addSession(session, loginID.toString());

        send302("/");
    }

    private void readUserCredentials() {

        try {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(),
                    "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            Map<String, String> inputs = parseFormData(formData);
            loginUserName = inputs.get("name");
            loginID = userDao.getUserByLogin(loginUserName).getID();
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
