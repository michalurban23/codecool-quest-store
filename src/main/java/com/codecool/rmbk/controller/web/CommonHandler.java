package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.dao.SQLMenuDAO;
import com.codecool.rmbk.dao.SQLSession;
import com.codecool.rmbk.dao.SQLUsers;
import com.codecool.rmbk.helper.CookieHandler;
import com.codecool.rmbk.helper.MimeTypeResolver;
import com.codecool.rmbk.model.Session;
import com.codecool.rmbk.model.usr.User;
import com.codecool.rmbk.view.WebDisplay;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class CommonHandler implements HttpHandler {

    private SQLMenuDAO menuDao = new SQLMenuDAO();
    String response;
    HttpExchange httpExchange;
    CookieHandler cookieHandler;
    Map<String,String> parsedURI;
    Map<String, String> mainMenu;
    Session session;
    User user;
    WebDisplay webDisplay = new WebDisplay();
    SQLSession sessionDao = new SQLSession();
    String urlList = "templates/list_content.twig";
    String urlItem = "templates/item.twig";
    String urlEdit = "templates/edit.twig";
    String urlAdd = "templates/add.twig";
    String urlJustList = "templates/list.twig";


    void send404() throws IOException {

        URL file404 = getFileURL("./static/404.html");

        if (file404 == null) {
            sendString("404: not found", 404);
        } else {
            sendFile(file404, 404);
        }
    }

    void send302(URI location) throws IOException {

        httpExchange.getResponseHeaders().set("Location", location.toString());
        httpExchange.sendResponseHeaders(302, -1);
    }

    void send302(String location) throws IOException {

        httpExchange.getResponseHeaders().set("Location", location);
        httpExchange.sendResponseHeaders(302, -1);
    }

    void send200(String response) throws IOException {

        sendString(response, 200);
    }

    void send200(URL fileURL) throws IOException {

        if (fileURL == null) {
            send401();
        } else {
            sendFile(fileURL, 200);
        }
    }

    void send403() throws IOException {

        URL file403 = getFileURL("./static/403.html");
        if (file403 == null) {
            sendString("403: access denied", 403);
        } else {
            sendFile(file403, 403);
        }

    }

    private void send401() throws IOException {

        URL file401 = getFileURL("./static/401.html");
        if (file401 == null) {
            sendString("401: session expired", 401);
        } else {
            sendFile(file401, 401);
        }
    }

    String validateRequest() throws IOException {

        String sessionId = cookieHandler.getSessionId();
        Boolean sessionExists = Session.sessionExists(sessionId);
        Boolean active = Session.isActive(sessionId);

        String requestStatus = null;

        if (sessionExists) {
            if (active) {
                requestStatus = user.getAccessLevel();
            } else {
                Session.removeSession(cookieHandler.getSessionId());
                sessionDao.removeSession(session);
                send401();
            }
        } else {
            send302("/login");
        }

        return requestStatus;
    }

    private void sendFile(URL fileURL, int httpCode) throws IOException {

        File file = new File(fileURL.getFile());

        MimeTypeResolver resolver = new MimeTypeResolver(file);
        String mime = resolver.getMimeType();

        httpExchange.getResponseHeaders().set("Content-Type", mime);
        httpExchange.sendResponseHeaders(httpCode, 0);

        OutputStream os = httpExchange.getResponseBody();

        FileInputStream fs = new FileInputStream(file);
        final byte[] buffer = new byte[0x10000];
        int count;
        while ((count = fs.read(buffer)) >= 0) {
            os.write(buffer,0,count);
        }
        os.close();
    }

    private void sendString(String response, int httpCode) throws IOException {

        httpExchange.sendResponseHeaders(httpCode, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    URL getFileURL(String path) {

        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResource(path);
    }

    void setConnectionData(HttpExchange httpExchange) {

        this.httpExchange = httpExchange;
        cookieHandler = new CookieHandler(httpExchange);
        session = Session.getSessionById(cookieHandler.getSessionId());

        if (session != null) {
            user = session.getUser();
            mainMenu = menuDao.getSideMenu(user);
        }
    }

    Map<String, String> parseFormData(String formData) throws IOException {

        Map<String, String> map = new LinkedHashMap<>();
        String[] pairs = formData.split("&");

        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            String key = URLDecoder.decode(keyValue[0], "UTF-8");
            String value = URLDecoder.decode(keyValue[1], "UTF-8");
            map.put(key, value);
        }
        return map;
    }

    Map<String,String> parseURIstring(String uriString) {

        String[] controlLevels = new String[] {"controller", "object", "action", "subject"};
        String[] uriElements = uriString.split("[/]");
        Map<String,String> resultMap = new HashMap<>();
        for (int i=0; i<uriElements.length; i++) {
            resultMap.put(controlLevels[i], uriElements[i]);
        }
        parsedURI = resultMap;
        return resultMap;
    }

    Map<String, String> prepareContextMenu(String[] options) {

        Map<String, String> menu = new HashMap<>();

        for (String option : options) {
            String url = "/" + parseURIstring(getRequestURI()).get("controller");
            if (option.equals("Add") || option.equals("Acquire")) {
                url += "/new";
            } else if (option.equals("Edit") || option.equals("Remove") || option.equals("LoginInfo")) {
                url += "/" + parseURIstring(getRequestURI()).get("object");
            }
            url += "/" + option.toLowerCase();
            menu.put(option, url);
        }
        return menu;
    }

    String getRequestURI() {

        String uriString = httpExchange.getRequestURI().toString();
        if (uriString.startsWith("/")) {
            uriString = uriString.substring(1);
        }
        if (uriString.endsWith("/")) {
            uriString = uriString.substring(0, uriString.length() - 1);
        }
        return uriString;
    }

    Map<String,String> readInputs() throws IOException {

        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(),
                "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String formData = br.readLine();

        return parseFormData(formData);
    }

    Boolean isObjectInstanceOfController(String controller, String object) {

        SQLUsers usersDao = new SQLUsers();

        String objectType = usersDao.getUserTypeByID(object);

        return objectType.toLowerCase().equals(controller);
    }
}
