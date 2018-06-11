package com.test;

import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

public class Test {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null);
        System.out.println("unleash the power");
        server.start();
        System.out.println("server unleashed");
    }
}