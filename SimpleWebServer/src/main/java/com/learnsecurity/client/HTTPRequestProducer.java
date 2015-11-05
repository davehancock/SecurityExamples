package com.learnsecurity.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;


public class HTTPRequestProducer {

    private static final Logger LOG = LoggerFactory.getLogger(HTTPRequestProducer.class);

    private static final String HTTPRequest = "GET ../OTF/key HTTP/1.0";

    private String host;

    private int port;

    public HTTPRequestProducer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void fireRequest() {

        try {
            Socket socket = new Socket(host, port);

            // Out
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF8");
            BufferedWriter bw = new BufferedWriter(osw);

            // In
            InputStream is = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));

            Thread.sleep(200);

            LOG.info("Writing out request: " + HTTPRequest);
            bw.write(HTTPRequest + "\r\n");
            bw.flush();

            String line;
            while ((line = in.readLine()) != null) {
                LOG.info("Reading in response: " + line);
            }

            bw.close();
            in.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}