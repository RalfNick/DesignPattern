package Rpc.demo.server;

import Rpc.RpcServer;

public class RpcServerApplication {

    public static void main(String[] args) throws Exception {
        RpcServer server = new RpcServer();
        CalculatorService calculatorService = new CalculatorServiceImpl();
        server.export(calculatorService, 1234);
    }
}
