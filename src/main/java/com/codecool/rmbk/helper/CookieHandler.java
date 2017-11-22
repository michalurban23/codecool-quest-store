package com.codecool.rmbk.helper;

import com.sun.net.httpserver.HttpExchange;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CookieHandler {

    private HttpExchange httpExchange;

    public CookieHandler(HttpExchange httpExchange) {

        this.httpExchange = httpExchange;
    }

    public String getSessionId() {

        String cookie = httpExchange.getRequestHeaders().getFirst("Cookie");

        if (cookie != null) {
            cookie = parseCookie(cookie).get("sessionId");
        }

        return cookie;
    }

    public String getSessionStatus() {

        String cookie = httpExchange.getRequestHeaders().getFirst("Cookie");

        if (cookie != null) {
            cookie = parseCookie(cookie).get("sessionStatus");
        }

        return cookie;
    }

    public String setNewSessionId() {

        String sessionId = PasswordHash.getSalt();
        String cookie = "sessionId=" + sessionId + createExpireString();

        httpExchange.getResponseHeaders().add("Set-Cookie", cookie);
        return sessionId;
    }

//    public void setStatusToLoggedOut() {
//
//        String cookie = "sessionStatus=loggedOut" + createExpireString();
//
//        httpExchange.getResponseHeaders().add("Set-Cookie",cookie);
//    }
//
//    public void setStatusToLoggedIn() {
//
//        String cookie = "sessionStatus=loggedIn" + createExpireString();
//
//        httpExchange.getResponseHeaders().add("Set-Cookie", cookie);
//    }

    private Map<String, String> parseCookie(String cookie) {

        Map<String, String> cookieMap = new HashMap<>();

        for (String data : cookie.split("; ")) {

            String[] entry = data.split("=");

            if (entry.length == 2) {
                cookieMap.put(entry[0], entry[1]);
            } else {
                cookieMap.put(entry[0], null);
            }
        }
        return cookieMap;
    }

//    public void clearCookie() {
//
//        String expired = "; expires=" + createDateInPast();
//
//        httpExchange.getResponseHeaders().add("Set-Cookie", "sessionId=" + expired);
//        httpExchange.getResponseHeaders().add("Set-Cookie", "sessionStatus=" + expired);
//    }

    private String createExpireString() {

        OffsetDateTime veryLongTime = OffsetDateTime.now(ZoneOffset.UTC).plus(Duration.ofMinutes(60));

        String cookieExpireTime = DateTimeFormatter.RFC_1123_DATE_TIME.format(veryLongTime);

        return "; expires= " + cookieExpireTime;
    }

//    private String createDateInPast() {
//
//        OffsetDateTime veryLongTime = OffsetDateTime.now(ZoneOffset.UTC).plus(Duration.ofMinutes(-5));
//
//        return DateTimeFormatter.RFC_1123_DATE_TIME.format(veryLongTime);
//    }

}
