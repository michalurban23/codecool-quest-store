package controller;

// import model.quest.QuestTemplate;
import model.usr.Student;
import view.UserView;

public class MentorController extends UserController{

    // private QuestTemplate questTemplate;
    private UserView userView = new UserView();

    public MentorController() {;}

    // public MentorController(User newUser){
    //
    //     user = newUser;
    // }
    //
    // public MentorController(QuestTemplate template){
    //
    //     questTemplate = template;
    // }

    public Student createNewStudent(){

        String[] str = userView.getStudentInfo("Creating new Student");
        return new Student(str);
    }

    // public QuestTemplate createNewQuestTemplate(){
    //
    //     int coins = getInt("Type amount of coins for this quest:");
    //     String newDescription = getInput("Type new quest description:");
    //     return new QuestTemplate(coins, newDescription);
    // }

}
