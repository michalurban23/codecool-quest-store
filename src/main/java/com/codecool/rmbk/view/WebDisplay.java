package com.codecool.rmbk.view;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import java.util.Map;

public class WebDisplay {

    public static String getSiteContent(String userName,
                                        Map<String, String> menu,
                                        Map<String, String> mainData,
                                        String url) {

        StringBuilder response = new StringBuilder();
        System.out.println(userName);
        response.append(getHeaderContent(userName));
        response.append(getMenuContent(menu));
        response.append(getMainContent(mainData, url));
        response.append(getFooterContent());

        return response.toString();
    }

    private static String getMainContent(Map<String, String> data, String url) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate(url);
        JtwigModel model = JtwigModel.newModel();

        model.with("data", data);

        return template.render(model);
    }

    private static String getHeaderContent(String userName) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/header.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("name", userName);

        return template.render(model);
    }

    private static String getFooterContent() {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/footer.twig");
        JtwigModel model = JtwigModel.newModel();

        return template.render(model);
    }

    private static String getMenuContent(Map<String, String> menu) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/menu.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("menu", menu);

        return template.render(model);
    }

    public static String getLoginScreen() {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/login.twig");
        JtwigModel model = JtwigModel.newModel();

        return template.render(model);
    }

    public static String getFailedLoginScreen() {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/fail.twig");
        JtwigModel model = JtwigModel.newModel();

        return template.render(model);
    }
}
