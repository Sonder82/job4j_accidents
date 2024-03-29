package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.Main;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class LoginControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void whenReturnLoginPage() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    @WithMockUser
    public void whenLoginIncorrectThenReturnError() throws Exception {
        String errorMessage = "Username or Password is incorrect !!";

        this.mockMvc.perform(get("/login?error=true"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("errorMessage", errorMessage))
                .andExpect(view().name("login"));
    }
}