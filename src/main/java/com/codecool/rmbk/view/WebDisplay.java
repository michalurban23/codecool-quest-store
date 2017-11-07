package com.codecool.rmbk.view;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.util.ArrayList;
import java.util.Map;

public class WebDisplay {

//    public String getSiteContent(ArrayList<String> entriesList, String URL) {
//
//        JtwigTemplate template = JtwigTemplate.classpathTemplate(URL);
//        JtwigModel model = JtwigModel.newModel();
//
//        ArrayList<String> dataToDisplay = new ArrayList<>();
//
//        for (String entry : entriesList) {
//            dataToDisplay.add(entry);
//        }
//
//        model.with("entries", dataToDisplay);
//
//        return template.render(model);
//    }

    public static String getSiteContent(Map<String, String> entriesMap, String URL) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate(URL);
        JtwigModel model = JtwigModel.newModel();

        model.with("map", entriesMap);

        return template.render(model);
    }
}
