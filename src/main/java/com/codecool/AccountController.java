package com.codecool;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;

public class AccountController implements HttpHandler {

    private String response;
    private HttpExchange httpExchange;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        this.response = "";
        this.httpExchange = httpExchange;

        if (isGetMethod()) {
//            redirectIfUserLogged();

            prepareStaticHtml();
        } else {
            handleData();
        }

        sendResponse();
    }

    private void handleData() {

    }

    private void prepareStaticHtml() {
        JtwigTemplate jtwigTemplate = JtwigTemplate.classpathTemplate("templates/loginPage.twig");
        JtwigModel jtwigModel = JtwigModel.newModel();
        this.response = jtwigTemplate.render(jtwigModel);
    }


    private boolean isGetMethod() {
        return httpExchange.getRequestMethod().equals("GET");
    }

    private void sendResponse() throws IOException {
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }


}
