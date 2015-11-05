package com.learnsecurity.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class SimpleWebServer {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleWebServer.class);

    private static ServerSocket dServerSocket;

    private static final int PORT = 8080;

    private int requestCount = 0;

    public SimpleWebServer() throws IOException {
        dServerSocket = new ServerSocket(PORT);
    }

    public static void main(String... args) throws IOException {

        SimpleWebServer simpleWebServer = new SimpleWebServer();
        simpleWebServer.startServer();
    }

    private void startServer() throws IOException {

        LOG.info("Starting Simple Web Server...");

        while (true) {

            Socket socket = dServerSocket.accept();

            new Thread(new HTTPRequestHandlerRunnable(socket)).start();
            LOG.info("Created new Thread for Request number: " + requestCount);

            requestCount++;
        }
    }

}
