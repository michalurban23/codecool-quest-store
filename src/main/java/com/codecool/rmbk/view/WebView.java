package com.codecool.rmbk.view;

import com.codecool.rmbk.model.usr.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import java.util.Map;

public abstract class WebView {

    private String header;
    private String footer;
    private String mainMenu;
    private String contextMenu;
    String mainContent;

    public void setHeader(User user) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/header.twig");
        JtwigModel model = JtwigModel.newModel();

        if (user != null) {
            model.with("name", user.getFullName());
        }

        header = template.render(model);
    }

    public void setFooter() {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/footer.twig");
        JtwigModel model = JtwigModel.newModel();

        footer = template.render(model);
    }


    public void setMainMenu(Map<String,String> optionMap) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/side_menu.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("menu", optionMap);

        mainMenu = template.render(model);
    }

    public void setContextMenu(Map<String,String> optionMap) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/context_menu.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("data", optionMap);

        contextMenu = template.render(model);
    }

    public String getResponse() {

        StringBuilder response = new StringBuilder();

        response.append(header);
        response.append(mainMenu);
        response.append(contextMenu);
        response.append(mainContent);
        response.append(footer);

        return response.toString();
    }

}