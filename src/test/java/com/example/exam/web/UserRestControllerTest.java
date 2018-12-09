package com.example.exam.web;

import com.example.exam.service.api.UserService;
import com.example.exam.to.UserTo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static com.example.exam.TestData.ADMIN_TO;
import static com.example.exam.TestData.NEW_USER_TO;
import static com.example.exam.TestData.USER_TO;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UserRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    private ObjectMapper mapper;
    private final String BASE_URL = "/rest/users/";

    @Before
    public void init(){
        mapper = new ObjectMapper();
    }

    @Test
    public void create() throws Exception {
        UserTo testUser = UserTo.builder()
                .email("testUser@mail.com")
                .name("testUser")
                .build();
        UserTo testUserWithId = UserTo.builder()
                .id(15)
                .email("testUser@mail.com")
                .name("testUser")
                .build();

        given(this.userService.save(testUser))
                .willReturn(testUserWithId);

        this.mvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(testUser)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(testUserWithId)));
    }

    @Test
    public void update() throws Exception {
        given(this.userService.save(USER_TO))
                .willReturn(USER_TO);

        this.mvc.perform(put(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(USER_TO)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(USER_TO)));
    }

    @Test
    public void getAll() throws Exception {
        given(this.userService.getAll())
                .willReturn(Arrays.asList(USER_TO, ADMIN_TO));

        this.mvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(Arrays.asList(USER_TO, ADMIN_TO))));
    }

    @Test
    public void getUser() throws Exception {
        Integer testId = USER_TO.getId();
        given(this.userService.getById(testId))
                .willReturn(USER_TO);

        this.mvc.perform(get(BASE_URL + testId))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(USER_TO)));
    }

    @Test
    public void deleteUser() throws Exception {
        Integer testId = USER_TO.getId();
        doNothing().when(this.userService).deleteById(testId);

        this.mvc.perform(delete(BASE_URL + testId))
                .andExpect(status().isOk());
    }
}