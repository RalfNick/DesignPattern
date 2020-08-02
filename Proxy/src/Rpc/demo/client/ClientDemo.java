package Rpc.demo.client;

import Rpc.RpcClient;

public class ClientDemo {

    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalculatorService service = client.refer(CalculatorService.class, "127.0.0.1", 1234);
        int result = service.add(1, 2);
        System.out.print("result = " + result);
    }
}
