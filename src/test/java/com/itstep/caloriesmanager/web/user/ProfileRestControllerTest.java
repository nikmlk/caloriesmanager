package com.itstep.caloriesmanager.web.user;

import com.itstep.caloriesmanager.TestUtil;
import com.itstep.caloriesmanager.model.Role;
import com.itstep.caloriesmanager.model.User;
import com.itstep.caloriesmanager.web.AbstractControllerTest;
import com.itstep.caloriesmanager.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.itstep.caloriesmanager.UserTestData.*;
import static com.itstep.caloriesmanager.web.user.ProfileRestController.REST_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProfileRestControllerTest extends AbstractControllerTest {

    @Test
    void testGet() throws Exception {
        TestUtil.print(
                mockMvc.perform(get(REST_URL))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andExpect(contentJson(USER))
        );
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL))
                .andExpect(status().isNoContent());
        assertMatch(userService.getAll(), ADMIN);
    }

    @Test
    void testUpdate() throws Exception {
        User updated = new User(USER_ID, "newName", "newemail@ya.ru", "newPassword", Role.ROLE_USER);
        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertMatch(new User(userService.getByEmail("newemail@ya.ru")), updated);
    }
}