package com.example.exam.service.impl;

import com.example.exam.exception.UserNotFoundException;
import com.example.exam.model.User;
import com.example.exam.repository.UserRepository;
import com.example.exam.service.api.UserService;
import com.example.exam.to.UserTo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserTo> getAll() {
        return repository.findAll().stream()
                .map(this::convertFromUser)
                .collect(Collectors.toList());
    }

    @Override
    public UserTo getById(Integer id) {
        return convertFromUser(repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not exist")));
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public UserTo save(UserTo user) {
        return convertFromUser(repository.save(convertFromUserTo(user)));
    }


    private UserTo convertFromUser(User user){
        return UserTo.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    private User convertFromUserTo(UserTo user){
        return User.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
