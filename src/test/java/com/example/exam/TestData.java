package com.example.exam;

import com.example.exam.model.User;
import com.example.exam.to.UserTo;

public class TestData {

    public static final UserTo USER_TO = UserTo.builder()
            .id(1)
            .name("user")
            .email("user@mail.ru")
            .build();

    public static final UserTo NEW_USER_TO = UserTo.builder()
            .id(null)
            .name("user")
            .email("user@mail.ru")
            .build();

    public static final UserTo ADMIN_TO = UserTo.builder()
            .id(2)
            .name("admin")
            .email("admin@mail.ru")
            .build();

    public static final User USER = User.builder()
            .id(1)
            .name("user")
            .email("user@mail.ru")
            .build();

    public static final User ADMIN = User.builder()
            .id(2)
            .name("admin")
            .email("admin@mail.ru")
            .build();
}
