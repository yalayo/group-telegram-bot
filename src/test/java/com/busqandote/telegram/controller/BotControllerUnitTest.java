package com.busqandote.telegram.controller;

import com.busqandote.telegram.BotController;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BotControllerUnitTest {
    private BotController botController = new BotController();

    @Test
    @DisplayName("If the message to be send is empty then return false")
    public void testMessageToSendEmpty() {
        String user = "test";
        assertFalse(botController.send("", user));
    }

    @Test
    @DisplayName("If the message to be send is not empty then return true")
    public void testSendMessage() {
        String user = "test";
        String message = "Test message";

        HttpClient httpClient = mock(HttpClient.class);
        HttpHost httpHost = mock(HttpHost.class);
        HttpRequest httpRequest = mock(HttpRequest.class);
        HttpResponse httpResponse = mock(HttpResponse.class);
        StatusLine statusLine = mock(StatusLine.class);

        try {
            when(httpClient.execute(httpHost, httpRequest)).thenReturn(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }

        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(200);

        botController.setHttpClient(httpClient);

        assertTrue(botController.send(message, user));
    }
}
