public class MentorController extends UserController{

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

}
