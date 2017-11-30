package com.codecool.rmbk.view.web;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

public class LoginWebView extends WebDisplay {

    public void setLoginScreen() {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/login.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("message", "Login to Queststore System:");

        mainContent = template.render(model);
    }
}
