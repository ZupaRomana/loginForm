package com.codecool;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class AccountController implements HttpHandler {

    private String response;
    private HttpExchange httpExchange;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        DAOLogin daoLogin = new DAOLogin();
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

    private void handleData() throws IOException {

        String formData = getFormData();
        Map<String, String> inputs = parseFormData(formData);
        String login = inputs.get("login");
        String password = inputs.get("password");

        System.out.println(login);
        System.out.println(password);
    }

    private String getFormData() throws IOException {
        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);

        return br.readLine();
    }

    private static Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String[] pairs = formData.split("&");
        for(String pair : pairs){
            String[] keyValue = pair.split("=");
            String value = new URLDecoder().decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
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
