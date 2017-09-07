package view;

import model.usr.*;

public interface UserView {

    public Integer handleMainMenu();
    public void showShortInfo(User user);
    public void showFullInfo(User user);
    public String[] getNewUserData();
}
