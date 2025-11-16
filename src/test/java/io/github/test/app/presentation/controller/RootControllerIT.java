package io.github.test.app.presentation.controller;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(RootController.class)
public class RootControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("integration")
    void getRootPage_withRequest_shouldReturnIndexPage() throws Exception {
        // arrange
        String requestPath = "/";

        // act & assert
        String expected = "pages/indexZ";
        mockMvc.perform(MockMvcRequestBuilders.get(requestPath)).andExpect(MockMvcResultMatchers.view().name(expected));
    }
}
