package com.codecool;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class AccountController implements HttpHandler {

    private String response;
    private HttpExchange httpExchange;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        this.response = "";
        this.httpExchange = httpExchange;

        if (isGetMethod()) {
            redirectIfUserLogged();
            prepareStaticHtml();
        } else {
            checkUserData();
        }
    }

    private void prepareStaticHtml() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("html/loginPage.html"));
        while (sc.hasNext()) {
            response += sc.nextLine();
        }
    }

    private boolean isGetMethod() {
        return httpExchange.getRequestMethod().equals("GET");
    }


}
