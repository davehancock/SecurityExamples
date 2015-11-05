
package com.learnsecurity.server;

import java.net.Socket;


public class HTTPRequestHandlerRunnable implements Runnable {

    private HTTPRequestHandler HTTPRequestHandler = new HTTPRequestHandler();

    private Socket socket;

    public HTTPRequestHandlerRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        HTTPRequestHandler.processRequest(socket);
    }

}
