
public class SimpleSingleton {

    private static final SimpleSingleton sSingleton = new SimpleSingleton();

    private SimpleSingleton() {
    }

    public static SimpleSingleton getInstance() {
        return sSingleton;
    }
}
