package com.example.exam.web;

import com.example.exam.service.api.UserService;
import com.example.exam.to.UserTo;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static com.example.exam.TestData.ADMIN_TO;
import static com.example.exam.TestData.USER_TO;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UserMvcControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void create() throws Exception {

        when(this.userService.save(new UserTo(null, "user", "user@mail.ru"))).thenReturn(USER_TO);

        this.mvc.perform(post("/users/create/")
                .contentType(APPLICATION_FORM_URLENCODED) //from MediaType
                .param("name", "user")
                .param("email", "user@mail.ru"))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));

        verify(this.userService, times(1)).save(new UserTo(null, "user", "user@mail.ru"));
    }

    @Test
    public void createWithException() throws Exception {

        when(this.userService.save(new UserTo(null, "user", "user@mail.ru"))).thenReturn(USER_TO);

        this.mvc.perform(post("/users/create/")
                .contentType(APPLICATION_FORM_URLENCODED) //from MediaType
                .param("id", "123")
                .param("name", "user")
                .param("email", "user@mail.ru"))

                .andExpect(status().isBadRequest());

    }

    @Test
    public void update() throws Exception {
        Integer testId = USER_TO.getId();
        when(this.userService.save(new UserTo(testId, "user", "user@mail.ru"))).thenReturn(USER_TO);

        this.mvc.perform(post("/users/update/")
                .contentType(APPLICATION_FORM_URLENCODED) //from MediaType
                .param("id", String.valueOf(testId))
                .param("name", "user")
                .param("email", "user@mail.ru"))

                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));

        verify(this.userService, times(1)).save(new UserTo(testId, "user", "user@mail.ru"));
    }

    @Test
    public void updateWithException() throws Exception {
        Integer testId = USER_TO.getId();
        when(this.userService.save(new UserTo(testId, "user", "user@mail.ru"))).thenReturn(USER_TO);

        this.mvc.perform(post("/users/update/")
                .contentType(APPLICATION_FORM_URLENCODED) //from MediaType
//                .param("id", String.valueOf(testId))
                .param("name", "user")
                .param("email", "user@mail.ru"))

                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAll() throws Exception {
        given(this.userService.getAll())
                .willReturn(Arrays.asList(USER_TO, ADMIN_TO));

        this.mvc.perform(get("/users"))

                .andExpect(status().isOk())
                .andExpect(model().attribute("users",
                        Matchers.contains(USER_TO, ADMIN_TO)))
                .andExpect(view().name("users"));
    }

    @Test
    public void showCreateForm() throws Exception {
        this.mvc.perform(get("/users/create"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("user",
                        Matchers.is(new UserTo())))
                .andExpect(view().name("create"));
    }

    @Test
    public void editUser() throws Exception {
        int userId = USER_TO.getId();

        given(this.userService.getById(userId))
                .willReturn(USER_TO);

        this.mvc.perform(get("/users/edit/" + userId))
                .andExpect(status().isOk())
                .andExpect(model().attribute("user",
                        Matchers.is(USER_TO)))
                .andExpect(view().name("edit"));
    }

    @Test
    public void deleteUser() throws Exception {
        int userId = USER_TO.getId();

        doNothing().when(this.userService).deleteById(userId);

        this.mvc.perform(get("/users/delete/" + userId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));

        verify(this.userService, times(1)).deleteById(userId);
    }
}