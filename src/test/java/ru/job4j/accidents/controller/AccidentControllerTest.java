package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.service.data.DataAccidentService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class AccidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DataAccidentService accidentService;

    @Test
    @WithMockUser
    public void whenReturnViewWithAllAccidents() throws Exception {
        this.mockMvc.perform(get("/accidents"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accidents/list"));
    }

    @Test
    @WithMockUser
    void whenReturnViewWithCreatePage() throws Exception {
        this.mockMvc.perform(get("/accidents/createAccident"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accidents/createAccident"));
    }

    @Test
    @WithMockUser
    void whenReturnViewWithEditPage() throws Exception {
        var accident = new Accident(1, "name1", new AccidentType(), Set.of(new Rule()),
                "description1", "address1");

        when(accidentService.findById(accident.getId())).thenReturn(Optional.of(accident));

        this.mockMvc.perform(get("/accidents/editAccident")
                .param("id", String.valueOf(accident.getId())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accidents/editAccident"));
    }

    @Test
    @WithMockUser
    void whenPostAccidentThenReturnAccidents() throws Exception {
        var idRules = List.of(1, 2);
        var accidentType = new AccidentType(1, "type1");
        var accident = new Accident(1, "name1", accidentType, Set.of(new Rule()),
                "description1", "address1");

        this.mockMvc.perform(post("/accidents/saveAccident")
                        .param("id", String.valueOf(accident.getId()))
                        .param("name", accident.getName())
                        .param("type.id", String.valueOf(accident.getType().getId()))
                        .param("rIds", idRules.get(0).toString(), idRules.get(1).toString())
                        .param("description", accident.getDescription())
                        .param("address", accident.getAddress()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/accidents"));

        ArgumentCaptor<Accident> accidentCaptor = ArgumentCaptor.forClass(Accident.class);
        ArgumentCaptor<List<Integer>> rIdsCaptor = ArgumentCaptor.forClass(List.class);

        verify(accidentService).save(accidentCaptor.capture(), rIdsCaptor.capture());

        assertThat(accidentCaptor.getValue().getName()).isEqualTo("name1");
    }
}