package com.codecool.rmbk.controller.web;

import com.codecool.rmbk.view.WebDisplay;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class ArtifactWebController extends CommonHandler{

    @Override
    public void handle(HttpExchange httpExchange) throws IOException{

        String url = null;

        if (user.getStatus().equals("Mentor")) {
            url = "templates/mentor_artifacts.twig";
        } else if (user.getStatus().equals("Student")) {
            url = "templates/student_artifacts.twig";
        }

        String response = WebDisplay.getSiteContent(user.getName(), null,
                null, url);

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
