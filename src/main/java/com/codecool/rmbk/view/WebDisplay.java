package com.codecool.rmbk.view;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import java.util.List;
import java.util.Map;

public class WebDisplay {

    public String getSiteContent(String userName,
                                 Map<String, String> mainMenu,
                                 Map<String, String> contextMenu,
                                 Map<String, String> mainData,
                                 String mainContentUrl) {


        StringBuilder response = new StringBuilder();
        response.append(getHeaderContent(userName));
        response.append(getTopMenuContent(mainMenu));
        response.append(getContextMenu(contextMenu));
        response.append(getMainContent(mainData, mainContentUrl));
        response.append(getFooterContent());

        return response.toString();
    }

    public String getSiteContent(String userName,
                                 Map<String, String> mainMenu,
                                 Map<String, String> contextMenu,
                                 String title,
                                 Map<String, String> mainData,
                                 String mainContentUrl) {

        StringBuilder response = new StringBuilder();
        response.append(getHeaderContent(userName));
        response.append(getTopMenuContent(mainMenu));
        response.append(getContextMenu(contextMenu));
        response.append(getMainContent(title, mainData, mainContentUrl));
        response.append(getFooterContent());

        return response.toString();
    }

    public String getSiteContent(String userName,
                                 Map<String, String> mainMenu,
                                 Map<String, String> contextMenu,
                                 String[] messages,
                                 Map<String, String> mainData,
                                 String mainContentUrl) {

        StringBuilder response = new StringBuilder();
        response.append(getHeaderContent(userName));
        response.append(getTopMenuContent(mainMenu));
        response.append(getContextMenu(contextMenu));
        response.append(getMainContent(messages, mainData, mainContentUrl));
        response.append(getFooterContent());

        return response.toString();
    }

    //this methods is for test to display messages in backlog properly
    public String getSiteContentMap(String userName,
                                 Map<String, String> mainMenu,
                                 Map<String, String> contextMenu,
                                 Map<String, String> messages,
                                 Map<String, String> mainData,
                                 String mainContentUrl) {

        StringBuilder response = new StringBuilder();
        response.append(getHeaderContent(userName));
        response.append(getTopMenuContent(mainMenu));
        response.append(getContextMenu(contextMenu));
        response.append(getMainContentMap(messages, mainData, mainContentUrl));
        response.append(getFooterContent());

        return response.toString();
    }

    private String getMainContentMap(Map<String, String> messages, Map<String, String> data, String url) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate(url);
        JtwigModel model = JtwigModel.newModel();
        model.with("messages", messages);
        model.with("data", data);

        return template.render(model);
    }
    //end methods




    public String getSiteContent(String userName,
                                 Map<String, String> mainMenu,
                                 Map<String, String> contextMenu,
                                 String title,
                                 List<String> mainData,
                                 String mainContentUrl) {

        StringBuilder response = new StringBuilder();
        response.append(getHeaderContent(userName));
        response.append(getTopMenuContent(mainMenu));
        response.append(getContextMenu(contextMenu));
        response.append(getMainContent(title, mainData, mainContentUrl));
        response.append(getFooterContent());

        return response.toString();
    }

    private String getMainContent(Map<String, String> data, String url) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate(url);
        JtwigModel model = JtwigModel.newModel();

        model.with("data", data);

        return template.render(model);
    }

    private String getMainContent(String title, Map<String, String> data, String url) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate(url);
        JtwigModel model = JtwigModel.newModel();
        model.with("title", title);
        model.with("messages", title);
        model.with("data", data);

        return template.render(model);
    }

    private String getMainContent(String[] messages, Map<String, String> data, String url) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate(url);
        JtwigModel model = JtwigModel.newModel();
        model.with("messages", messages);
        model.with("data", data);

        return template.render(model);
    }

    private String getMainContent(List<String> data, String url) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate(url);
        JtwigModel model = JtwigModel.newModel();

        model.with("data", data);

        return template.render(model);
    }

    private String getMainContent(String title, List<String> data, String url) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate(url);
        JtwigModel model = JtwigModel.newModel();

        model.with("title", title);
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

    private String getTopMenuContent(Map<String, String> menu) {

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