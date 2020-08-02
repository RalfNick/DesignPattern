package DynamicProxy;

/**
 * 静态代理
 */
public class BirdTimeProxy implements Flyable {

    private final Flyable mFlyable;

    public BirdTimeProxy(Flyable flyable) {
        this.mFlyable = flyable;
    }

    @Override
    public void fly() {
        System.out.println("飞行起始时间 - " + System.currentTimeMillis());
        mFlyable.fly();
        System.out.println("飞行结束时间 - " + System.currentTimeMillis());
    }
}
