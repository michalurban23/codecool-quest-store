package com.codecool.rmbk.view;

import com.codecool.rmbk.model.usr.Group;

public class ConsoleGroupView extends ConsoleView {

    public String getNewGroupName (Group group) {

        String groupType = group.getClass().getSimpleName();

        return getString("Enter new " + groupType + " name: ");
    }

}
