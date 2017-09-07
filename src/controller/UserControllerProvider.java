package controller;

public class UserControllerProvider {

    private List<UserController> userControllers = new ArrayList<>();

    public UserControllerProvider() {
        userControllers.add(new StudentController());
        userControllers.add(new MentorController());
        userControllers.add(new AdminController());
    }

    private UserController getByUserType(String userType) {
        for (UserController controller : userControllers) {
            if (controller.getUserType().equals(userType)) {
                return controller;
            }
            else {
                return null;
            }
        }
    }

}
