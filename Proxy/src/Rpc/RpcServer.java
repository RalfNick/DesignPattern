package Rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class RpcServer {

    public void export(final Object service, final int port) throws Exception {
        if (service == null) {
            throw new IllegalArgumentException("service instance is null");
        }
        if (port <= 0 || port >= 65536) {
            throw new IllegalArgumentException("Invalid port " + port);
        }
        final ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            final Socket socket = serverSocket.accept();
            new Thread(() -> {
                try {
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    try {
                        String methodName = ois.readUTF();
                        Class<?>[] parameterTypes = (Class<?>[]) ois.readObject();
                        Object[] arguments = (Object[]) ois.readObject();
                        Method method = service.getClass().getMethod(methodName, parameterTypes);
                        Object result = method.invoke(service, arguments);
                        oos.writeObject(result);
                    } catch (Throwable throwable) {
                        oos.writeObject(throwable);
                    } finally {
                        oos.close();
                        ois.close();
                        socket.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
