package com.codecool.rmbk.helper;

public class StringParser {

    public static String removeWhitespaces(String original) {

        StringBuilder newString = new StringBuilder();

        for (char ch: original.toCharArray()) {
            if (ch == ' ') {
                newString.append("_");
            } else {
                newString.append(ch);
            }
        }
        return newString.toString();
    }

    public static String addWhitespaces(String original) {

        StringBuilder newString = new StringBuilder();

        for (char ch: original.toCharArray()) {
            if (ch == '_') {
                newString.append(" ");
            } else {
                newString.append(ch);
            }
        }
        return newString.toString();
    }

}
