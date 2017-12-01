package com.codecool.rmbk.view;

import com.codecool.rmbk.model.usr.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import java.util.Map;

public class UserWebView extends WebView {

    public void setUserDetailsView (User object) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/main_user.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("data", object.getFullInfoMap());

        mainContent = (template.render(model));
    }

    public void setUsersListView(Map<String,String> usersMap) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/list_content.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("data", usersMap);

        mainContent = (template.render(model));
    }

    public void setAddUserView (String userType) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/add.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("title", String.format("Add new %s", userType));
        model.with("data", User.getFieldLabels());

        mainContent = (template.render(model));
    }

    public void setEditUserDataView (Map<String,String> valuesMap) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/edit.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("title", "Edit account data");
        model.with("data", valuesMap);

        mainContent = (template.render(model));
    }

}