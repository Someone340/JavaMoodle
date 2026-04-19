package org.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.spring.DTO.UserDTO;
import org.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(UserController.class)
class UserControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAll() throws Exception {
        List<UserDTO> users = Arrays.asList(
                new UserDTO(0, "Test1", "test1@mail.ru", 20),
                new UserDTO(1, "Test2", "test2@mail.ru", 30)
        );
        when(userService.findAll()).thenReturn(users);

        ResultActions result = mockMvc.perform(get("/api/users"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test1"))
                .andExpect(jsonPath("$[1].age").value(30));
    }

    @Test
    void findById() throws Exception {
        UserDTO user = new UserDTO(0, "Тест", "test@mail.ru", 20);
        when(userService.findById(0)).thenReturn(user);

        ResultActions result = mockMvc.perform(get("/api/users/{id}", 0));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0))
                .andExpect(jsonPath("$.email").value("test@mail.ru"));
    }

    @Test
    void create() throws Exception {
        UserDTO user = new UserDTO(1, "Test", "test@mail.ru", 20);
        String json = objectMapper.writeValueAsString(user);
        when(userService.create(any())).thenReturn(user);

        ResultActions result = mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json));

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test"));
    }

    @Test
    void update() throws Exception {
        UserDTO user = new UserDTO(0, "Test", "test@mail.ru", 20);
        String json = objectMapper.writeValueAsString(user);
        when(userService.update(eq(1), any(UserDTO.class))).thenReturn(user);

        ResultActions result = mockMvc.perform(put("/api/users/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test"))
                .andExpect(jsonPath("$.age").value(20));
    }

    @Test
    void deleteTest() throws Exception {
        doNothing().when(userService).delete(0);

        ResultActions result = mockMvc.perform(delete("/api/users/{id}", 0));

        result.andExpect(status().isNoContent());
    }
}
