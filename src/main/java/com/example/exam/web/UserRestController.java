package com.example.exam.web;

import com.example.exam.service.api.UserService;
import com.example.exam.to.UserTo;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/users/")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserTo> create(@Validated(UserTo.CreateUser.class) @RequestBody UserTo userTo){
        return ResponseEntity.ok(userService.save(userTo));
    }

    @PutMapping
    public ResponseEntity<UserTo> update(@Validated(UserTo.UpdateUser.class) @RequestBody UserTo userTo){
        return ResponseEntity.ok(userService.save(userTo));
    }

    @GetMapping
    public ResponseEntity<List<UserTo>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserTo> getById(@PathVariable ("id") int id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable ("id") int id){
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
