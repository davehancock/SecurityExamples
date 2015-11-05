package com.learnsecurity.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HTTPRequestProducerGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(HTTPRequestProducerGenerator.class);

    private static final int REQUESTS = 499;

    public static void main(String... args) {

        HTTPRequestProducerGenerator generator = new HTTPRequestProducerGenerator();
        generator.generate();
    }

    public void generate() {

        for (int i = 0; i < REQUESTS; i++) {
            LOG.info("Creating Request Number: " + (i));
            new Thread(new HTTPRequestProducerRunnable()).start();
        }
    }

}

