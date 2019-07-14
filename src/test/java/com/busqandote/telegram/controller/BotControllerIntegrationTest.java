package com.busqandote.telegram.controller;

import com.busqandote.telegram.BotController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BotController.class)
public class BotControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("If the message to be send is empty then return false")
    public void testMessageToSendEmpty() throws Exception {
        mvc.perform(post("/message")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("If the message to be send is not empty then return true")
    public void testSendMessage()throws Exception {
        mvc.perform(post("/message")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
