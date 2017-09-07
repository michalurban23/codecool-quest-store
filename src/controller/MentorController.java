package controller;

import model.quest.QuestTemplate;
import model.usr.Student;

public class MentorController extends UserController{

    private QuestTemplate questTemplate;
    private UserView = new UserView();

    public MentorController() {;}

    public MentorController(User newUser){

        user = newUser;
    }

    public MentorController(QuestTemplate template){

        questTemplate = template;
    }

    public User createNewUser(){

        String[] str = userView.getUserData("Student");
        return new Student(str);
    }

    public QuestTemplate createNewQuestTemplate(){

        int coins = getInt("Type amount of coins for this quest:");
        String newDescription = getInput("Type new quest description:");
        return new QuestTemplate(coins, newDescription);
    }

}
