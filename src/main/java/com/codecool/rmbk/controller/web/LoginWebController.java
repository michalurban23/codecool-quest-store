package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLLoginDAO;
import com.codecool.rmbk.dao.SQLUsers;
import com.codecool.rmbk.model.usr.User;
import com.codecool.rmbk.view.WebDisplay;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class LoginWebController implements HttpHandler {

    private String response;
    private String loginUserName;
    private String loginPassword;
    private SQLLoginDAO dataAccess;
    private SQLUsers userDao;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        setupLoginProcess();
        String method = httpExchange.getRequestMethod();

        if (method.equals("GET")) {
            response = WebDisplay.getLoginScreen();
        } else if (method.equals("POST")) {
            readUserCredentials(httpExchange);
            if (dataAccess.login(loginUserName, loginPassword)) {
                logUserIn(httpExchange);
            } else {
                response = WebDisplay.getUnsuccessfulLoginScreen();
            }
        }

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void logUserIn(HttpExchange httpExchange) throws IOException {

        User loggedUser = userDao.getUserByLogin(loginUserName);
        Session session = new Session(loggedUser);
        httpExchange.sendResponseHeaders(303, 0);
        System.out.println("dupa");
    }

    private void readUserCredentials(HttpExchange httpExchange) {

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

    private Map<String, String> parseFormData(String formData) throws IOException {

        Map<String, String> map = new HashMap<>();
        String[] pairs = formData.split("&");

        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            String value = URLDecoder.decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }

    private void setupLoginProcess() {

        SQLLoginDAO.setPermission();
        dataAccess = new SQLLoginDAO();
        userDao = new SQLUsers();
    }

}
