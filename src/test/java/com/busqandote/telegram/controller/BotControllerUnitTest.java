package com.busqandote.telegram.controller;

import com.busqandote.telegram.BotController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BotControllerUnitTest {
    private BotController botController = new BotController();;

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

        assertTrue(botController.send(message, user));
    }
}
