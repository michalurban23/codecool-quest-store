package com.codecool.rmbk.controller.web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.IOException;

public class ControllerRouter extends CommonHandler {

    public void handle(HttpExchange httpExchange) throws IOException {

        setHttpExchange(httpExchange);
        validateRequest();
        parseURIstring(getRequestURI());

        String controller = user.getClass().getSimpleName().toLowerCase();
        String object = String.valueOf(user.getID());
        System.out.println(">" + object + "<");
        System.out.println(object.length());
        send302(String.format("/%s/%s", controller, object));
    }
}
