package com.example.exam.service.api;

import com.example.exam.to.UserTo;

import java.util.List;

public interface UserService {

    List<UserTo> getAll();
    UserTo getById(Integer id);
    void deleteById(Integer id);
    UserTo save(UserTo user);
}
