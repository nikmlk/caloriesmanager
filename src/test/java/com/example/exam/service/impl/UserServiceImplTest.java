package com.example.exam.service.impl;

import com.example.exam.model.User;
import com.example.exam.repository.UserRepository;
import com.example.exam.service.api.UserService;
import com.example.exam.to.UserTo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.example.exam.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @MockBean
    private UserRepository repository;

    private UserService service;

    @Before
    public void init() {
        service = new UserServiceImpl(repository);
    }

    @Test
    public void getAll() {
        given(this.repository.findAll())
                .willReturn(Arrays.asList(USER, ADMIN));

        List<UserTo> actual = service.getAll();

        assertThat(actual).isEqualTo(Arrays.asList(USER_TO, ADMIN_TO));
    }

    @Test
    public void getById() {

        int testId = 8;
        given(this.repository.findById(testId))
                .willReturn(Optional.of(USER));

        UserTo actual = service.getById(testId);

        assertThat(actual).isEqualTo(USER_TO);
    }

    @Test
    public void deleteById() {

        int testId = 8;
        doNothing().when(this.repository).deleteById(testId);

        service.deleteById(testId);

        verify(this.repository, times(1)).deleteById(testId);
    }

    @Test
    public void save() {
        given(this.repository.save(USER))
                .willReturn(USER);

        UserTo actual = service.save(USER_TO);

        assertThat(actual).isEqualTo(USER_TO);
    }
}