package com.test.webroute;

import com.sun.net.httpserver.HttpExchange;
import com.test.WebRoute;

import java.io.IOException;
import java.io.OutputStream;

public class WebRouteImplementation {
    public void buildPage(HttpExchange request, String response) throws IOException {
        request.sendResponseHeaders(200, response.length());
        OutputStream os = request.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    @WebRoute("/list")
    public void onList(HttpExchange request) throws IOException {
        String response = "lista";
        buildPage(request, response);
    }

    @WebRoute("/")
    public void onMain(HttpExchange request) throws IOException{
        String response = "index";
        buildPage(request, response);
    }

    @WebRoute("/wtf")
    public void onWtf(HttpExchange request) throws IOException{
        String response = "wtf";
        buildPage(request, response);
    }

}
