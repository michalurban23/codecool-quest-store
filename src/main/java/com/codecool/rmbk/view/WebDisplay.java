package com.codecool.rmbk.view;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import java.util.Map;

public class WebDisplay {

    public String getSiteContent(String userName,
                                 Map<String, String> mainMenu,
                                 Map<String, String> contextMenu,
                                 Map<String, String> mainData,
                                 String mainContentUrl) {

        StringBuilder response = new StringBuilder();
        response.append(getHeaderContent(userName));
        response.append(getSideMenuContent(mainMenu));
        response.append(getContextMenu(contextMenu));
        response.append(getMainContent(mainData, mainContentUrl));
        response.append(getFooterContent());

        return response.toString();
    }

    private String getMainContent(Map<String, String> data, String url) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate(url);
        JtwigModel model = JtwigModel.newModel();

        model.with("data", data);

        return template.render(model);
    }

    private String getContextMenu(Map<String, String> data) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/context_menu.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("data", data);

        return template.render(model);
    }

    private String getHeaderContent(String userName) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/header.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("name", userName);

        return template.render(model);
    }

    private String getFooterContent() {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/footer.twig");
        JtwigModel model = JtwigModel.newModel();

        return template.render(model);
    }

    private String getSideMenuContent(Map<String, String> menu) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/side_menu.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("menu", menu);

        return template.render(model);
    }

    public String getLoginScreen(String message) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/login.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("message", message);

        StringBuilder response = new StringBuilder();
        response.append(getHeaderContent("to QuestStore"));
        response.append(template.render(model));
        response.append(getFooterContent());

        return response.toString();
    }

}
