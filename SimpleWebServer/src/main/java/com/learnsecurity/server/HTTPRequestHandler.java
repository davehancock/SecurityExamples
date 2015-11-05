package com.learnsecurity.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;


public class HTTPRequestHandler {

    private static final Logger LOG = LoggerFactory.getLogger(HTTPRequestHandler.class);

    public void processRequest(Socket socket) {

        LOG.info("Handling Request.");

        try {
            Thread.sleep(100000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        BufferedReader br = null;
        OutputStreamWriter osw = null;

        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            osw = new OutputStreamWriter(socket.getOutputStream());

            String request = br.readLine();

            StringTokenizer st = new StringTokenizer(request, " ");
            String command = st.nextToken();
            String pathname = st.nextToken();

            if (command.equals("GET")) {
                //serveFile(osw, pathname);
                writeSimpleResponse(osw, pathname);
            } else {
                osw.write("HTTP/1.0 501 Not Implemented\n\n");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {

            try {
                osw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void writeSimpleResponse(OutputStreamWriter osw, String pathname) throws IOException {

        osw.write("Hey! You requested: " + pathname);
    }

    void serveFile(OutputStreamWriter osw,
                   String pathname) throws Exception {

        FileReader fr = null;
        int c = -1;

        StringBuffer sb = new StringBuffer();

        if (pathname.charAt(0) == '/')
            pathname = pathname.substring(1);

        if (pathname.equals(""))
            pathname = "index.html";

        try {
            fr = new FileReader(pathname);
            c = fr.read();
        } catch (Exception e) {
            osw.write("HTTP/1.0 404 Not Found\n\n");
            return;
        }

        osw.write("HTTP/1.0 200 OK\n\n");
        while (c != -1) {
            sb.append((char) c);
            c = fr.read();
        }
        osw.write(sb.toString());
    }

}