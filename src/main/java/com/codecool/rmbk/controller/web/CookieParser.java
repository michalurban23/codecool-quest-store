package com.codecool.rmbk.controller.web;

import com.sun.net.httpserver.HttpExchange;
import java.net.HttpCookie;

class CookieParser {

    private static HttpCookie cookie;

    static String readCookieString(HttpExchange httpExchange) {

        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");

        if (cookieStr != null) {
            cookie = HttpCookie.parse(cookieStr).get(0);
            return cookie.toString();
        }
        return null;
    }

    static HttpCookie readCookie(HttpExchange httpExchange) {

        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");

        if (cookieStr != null) {
            cookie.setValue(parseCookieData(cookieStr));
            return cookie;
        }
        return null;
    }

    static String getSessionID (HttpCookie cookie) {

        int cookieIdStart = 11;
        int cookieIdEnd = 31;

        return cookie.toString().substring(cookieIdStart, cookieIdEnd);
    }

    static void createCookie(HttpExchange httpExchange, String sessionID) {

        cookie = new HttpCookie("sessionId", sessionID);
        httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
        System.out.println("created cookie: " + cookie.toString());
    }

    static private String parseCookieData(String cookieStr) {

        String[] info = cookieStr.split("sessionId=");
        return info[1].substring(1, info[1].length()-1);
    }
}