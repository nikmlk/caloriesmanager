package com.example.exam.web;

import com.example.exam.service.api.UserService;
import com.example.exam.to.UserTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserMvcController {

    private final UserService userService;

    public UserMvcController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public String create(@Validated(UserTo.CreateUser.class) UserTo userTo) {
        userService.save(userTo);
        return "redirect:/users";
    }

    @PostMapping("/update")
    public String update(@Validated(UserTo.UpdateUser.class) UserTo userTo) {
        userService.save(userTo);
        return "redirect:/users";
    }

    @GetMapping
    public String getAll(Model model) {
        List<UserTo> userList = userService.getAll();
        model.addAttribute("users", userList);
        return "users";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new UserTo());
        return "create";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        UserTo user = userService.getById(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}
