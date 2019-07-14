package com.busqandote.telegram;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.IOException;

public class BotController {
    private HttpClient httpClient;

    public BotController() {}

    public boolean send(String message, String user) {
        if(message.isEmpty())
            return false;

        String base = "https://api.telegram.org/bot";
        String token = "";
        String method = "";

        String url = base + token + method;
        HttpPost httpPost = new HttpPost(url);

        String json = "{chat_id:" + user + ","
        + "text: " + message + ","
                + "}";

        try {
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            HttpResponse response = httpClient.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();

            if(statusLine.getStatusCode() != 200)
                return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
