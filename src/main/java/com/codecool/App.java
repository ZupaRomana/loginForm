package com.codecool;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App 
{
    public static void main( String[] args )
    {
        HttpServer httpServer = null;
        try {
            httpServer = HttpServer.create(new InetSocketAddress(8000), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        httpServer.createContext("/index", new AccountController());
        httpServer.createContext("/static", new Static());

        httpServer.setExecutor(null);

        httpServer.start();

    }
}