package StaticProxy;

public class UserControllerImpl implements IUserController {

    @Override
    public void login() {
        System.out.println("user login");
    }

    @Override
    public void logout() {
        System.out.println("user logout");
    }
}
