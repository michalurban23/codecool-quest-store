package com.codecool.rmbk.controller.web;

import com.sun.net.httpserver.HttpExchange;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.Map;

public class CookieParser {

    private static HttpCookie cookie;
    private static String cookieName;
    private static String cookieValue;

    static void readCookie(HttpExchange httpExchange) {

        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        Map<String, String> cookieInfo;

        if (cookieStr != null) {
            cookie = HttpCookie.parse(cookieStr).get(0);
            System.out.println(HttpCookie.parse(cookieStr).size());
            cookieInfo = parseData();
        }
    }

    static void createCookie(HttpExchange httpExchange) {

        String cookieValue = "";
        cookie = new HttpCookie("sessionId", cookieValue);
        httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
    }

    private static Map<String, String> parseData() {

        System.out.println(cookie.getValue());
        String[] data = cookie.getValue().split("");

        return null;
    }

}