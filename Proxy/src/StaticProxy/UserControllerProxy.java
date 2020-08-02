package StaticProxy;

public class UserControllerProxy implements IUserController {

    private IUserController mUserController;

    public UserControllerProxy(IUserController userController) {
        this.mUserController = userController;
    }

    @Override
    public void login() {
        System.out.println("before user login");
        mUserController.login();
        System.out.println("after user login");
    }

    @Override
    public void logout() {
        System.out.println("before user logout");
        mUserController.logout();
        System.out.println("after user logout");
    }
}
