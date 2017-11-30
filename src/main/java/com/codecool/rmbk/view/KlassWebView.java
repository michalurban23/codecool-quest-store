package com.codecool.rmbk.view;
import com.codecool.rmbk.model.usr.Group;
import com.codecool.rmbk.model.usr.Klass;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

public class KlassWebView extends WebView {

    public void setClassDetailsView (Klass object) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/class.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("class_name", object.getName());
        model.with("mentor", object.getMentor().getFullName());
        model.with("mentor_link", object.getMentor().getURI());
        model.with("data", object.getMembersURIMap());
        mainContent = (template.render(model));
    }

    public void setClassListView(Map<String,String> usersMap) {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/list.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("data", usersMap);
        mainContent = (template.render(model));
    }

    public void setAddUserView (Map<String,String> usersMap) {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/multiple_choose_from_list.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("title", "Choose students to add");
        model.with("data", usersMap);
        mainContent = (template.render(model));
    }

    public void setAssignMentorView (Map<String,String> usersMap) {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/choose_from_list.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("title", "Choose mentor to assign");
        model.with("data", usersMap);
        mainContent = (template.render(model));
    }

    public void setAddClassView () {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/add.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("title", "Add new class");
        model.with("data", Group.getFieldLabels());
        mainContent = (template.render(model));
    }

    public void setEditClassDataView (Klass object) {

        Map<String,String> infoMap = new LinkedHashMap<>();
        infoMap.put("name", object.getName());

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/edit.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("title", "Edit class info");
        model.with("data", infoMap);
        mainContent = (template.render(model));
    }
}