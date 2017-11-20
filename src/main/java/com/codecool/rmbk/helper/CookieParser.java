package com.codecool.rmbk.helper;

import com.sun.net.httpserver.HttpExchange;
import java.net.HttpCookie;

public class CookieParser {

    private static HttpCookie cookie;

    public static String readCookieString (HttpExchange httpExchange) {

        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");

        if (cookieStr != null) {
            cookie = HttpCookie.parse(cookieStr).get(0);
            return cookie.toString();
        }
        return null;
    }

    public static HttpCookie readCookie(HttpExchange httpExchange) {

        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        System.out.println("Całe cookie : " + cookieStr);

        if (cookieStr != null) {
            if (cookieStr.contains("session")) {
                cookie = new HttpCookie("sessionId", parseCookieData(cookieStr));
                return cookie;
            }
        }
        return null;
    }

    public static String getSessionID (HttpCookie cookie) {

        int cookieIdStart = 11;
        int cookieIdEnd = 31;

        return cookie.toString().substring(cookieIdStart, cookieIdEnd);
    }

    public static void createCookie(HttpExchange httpExchange, String sessionID) {

        cookie = new HttpCookie("sessionId", sessionID);
        httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
        System.out.println("created cookie: " + cookie.toString());
    }

    public static String parseCookieData(String cookieStr) {

        String[] info = cookieStr.split("sessionId=");
        return info[1].substring(1, info[1].length()-1);
    }
}