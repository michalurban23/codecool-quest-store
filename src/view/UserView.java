package view;

public class UserView extends ConsoleView{

    public UserView() {;}

    public String[] getStudentInfo(String message){

        System.out.println(message);
        String[] studentInfo = new String[]{"first name", "last name", "email", "address"};
        String[] newStudentInfo = new String[4];
        for(int i = 0; i < 4; i++){
            newStudentInfo[i] = getString("Type new user's " + studentInfo[i] + ": ");
        }
        return newStudentInfo;
    }

}
