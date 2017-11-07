package com.codecool.rmbk.view;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.util.ArrayList;
import java.util.Map;

public class WebDisplay {

    public static String getSiteContent(Map<String, String> entriesList, String URL) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate(URL);
        JtwigModel model = JtwigModel.newModel();

        model.with("map", entriesList);

        return template.render(model);
    }

    public static String getSiteContent(String URL) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate(URL);
        JtwigModel model = JtwigModel.newModel();

        return template.render(model);
    }

}
