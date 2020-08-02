package StaticProxy;

public class StaticProxyDemo {

    public static void main(String[] args) {
        IUserController userController = new UserControllerImpl();
        IUserController userControllerProxy = new UserControllerProxy(userController);
        userControllerProxy.login();
        userControllerProxy.logout();
    }
}
