package com.learnsecurity.client;

/**
 * @author David Hancock
 */
public class HTTPRequestProducerRunnable implements Runnable {

    private static final String HOST = "localhost";

    private static final int PORT = 8080;

    @Override
    public void run() {

        HTTPRequestProducer httpRequestProducer =
                new HTTPRequestProducer(HOST, PORT);

        httpRequestProducer.fireRequest();
    }

}
