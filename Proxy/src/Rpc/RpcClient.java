package Rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class RpcClient {

    public <T> T refer(Class<T> serviceClass, String host, int port) {
        if (serviceClass == null) {
            throw new IllegalArgumentException("serviceClass == null");
        }
        if (!serviceClass.isInterface()) {
            throw new IllegalArgumentException("The " + serviceClass.getName() + " must be interface class");
        }
        if (host == null || host.length() == 0) {
            throw new IllegalArgumentException("The host can not be null");
        }
        if (port <= 0 || port >= 65536) {
            throw new IllegalArgumentException("Invalid port " + port);
        }
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(), new Class[]{serviceClass}, (proxy, method, args) -> {
            final Socket socket = new Socket(host, port);
            try {
                final ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                try {
                    oos.writeUTF(method.getName());
                    oos.writeObject(method.getParameterTypes());
                    oos.writeObject(args);
                    final ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    try {
                        Object result = ois.readObject();
                        if (result instanceof Throwable) {
                            throw (Throwable) result;
                        }
                        return result;
                    } finally {
                        ois.close();
                    }
                } finally {
                    oos.close();
                }
            } finally {
                socket.close();
            }
        });
    }
}
