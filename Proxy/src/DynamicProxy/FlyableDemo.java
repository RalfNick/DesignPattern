package DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class FlyableDemo {

    public static void main(String[] args) {
        final Flyable bird = new Bird();
//        Flyable timeProxy = new BirdTimeProxy(bird);
//        timeProxy.fly();
//        Flyable logProxy = new BirdLogProxy(bird);
//        logProxy.fly();

        // 完成上面同样的效果，如果再有其他的需求，可以任意调整
        Flyable proxyInstance = (Flyable) Proxy.newProxyInstance(Flyable.class.getClassLoader(), new Class[]{Flyable.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("飞行日志记录1");
                System.out.println("飞行起始时间 - " + System.currentTimeMillis());
                method.invoke(bird, args);
                System.out.println("飞行日志记录2");
                System.out.println("飞行结束时间 - " + System.currentTimeMillis());
                return null;
            }
        });

        proxyInstance.fly();
    }
}
