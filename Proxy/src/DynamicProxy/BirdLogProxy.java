package DynamicProxy;

/**
 * 静态代理
 */
public class BirdLogProxy implements Flyable {

    private final Flyable mFlyable;

    public BirdLogProxy(Flyable flyable) {
        this.mFlyable = flyable;
    }

    @Override
    public void fly() {
        System.out.println("飞行日志记录1");
        mFlyable.fly();
        System.out.println("飞行日志记录2");
    }
}
