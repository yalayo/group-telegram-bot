package com.busqandote.telegram;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;

@RestController
@EnableWebMvc
public class BotController {
    private HttpClient httpClient;

    public BotController() {
        httpClient = HttpClientBuilder.create().build();
    }

    @PostMapping("/message")
    public boolean send(@RequestParam("message")String message, @RequestParam("user")String user) {
        if(message.isEmpty())
            return false;

        String base = "https://api.telegram.org/bot";
        String token = "853657759:AAHGMUKfPBbBJzPobZ5rUFXL1j-yGQd6jbo";
        String method = "sendMessage";

        String url = base + token + "/" + method;
        HttpPost httpPost = new HttpPost(url);

        JSONObject jo = new JSONObject();
        jo.put("chat_id", user);
        jo.put("text", message);

        try {
            StringEntity entity = new StringEntity(jo.toString());
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
