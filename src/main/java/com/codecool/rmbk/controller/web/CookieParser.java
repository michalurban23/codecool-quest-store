package com.codecool.rmbk.controller.web;

import com.sun.net.httpserver.HttpExchange;
import java.net.HttpCookie;

class CookieParser {

    private static HttpCookie cookie;

    static String readCookie(HttpExchange httpExchange) {

        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");

        if (cookieStr != null) {
            cookie = HttpCookie.parse(cookieStr).get(0);
            System.out.println(HttpCookie.parse(cookieStr).size());
            return cookie.toString();
        }
        return null;
    }

    static void createCookie(HttpExchange httpExchange, String sessionID) {

        cookie = new HttpCookie("sessionId", sessionID);
        httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
    }

}