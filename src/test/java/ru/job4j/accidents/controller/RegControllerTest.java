package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.Main;
import ru.job4j.accidents.model.Authority;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.service.AuthorityService;
import ru.job4j.accidents.service.data.DataUserService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class RegControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorityService authorityService;

    @MockBean
    private DataUserService userService;

    @Test
    @WithMockUser
    public void whenReturnRegPage() throws Exception {
        this.mockMvc.perform(get("/register"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    @WithMockUser
    public void whenPostRegPageThenReturnErrorMessage() throws Exception {
        String errorMessage = "пользователь с таким именем уже существует";
        Authority authority = new Authority(1, "ROLE_USER");
        User user = new User(1, "password", "username", authority, true);
        when(authorityService.findByAuthority(authority.getAuthority())).thenReturn(authority);
        when(userService.save(user)).thenReturn(Optional.empty());

        this.mockMvc.perform(post("/register")
                .param("username", user.getUsername())
                .param("password", user.getPassword()))
                .andDo(print())
                .andExpect(model().attribute("errorMessage", user.getUsername() + " " + errorMessage))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));

        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userService).save(argumentCaptor.capture());

        assertThat(argumentCaptor.getValue().getUsername()).isEqualTo(user.getUsername());
    }
}