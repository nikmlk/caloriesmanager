package com.itstep.caloriesmanager;

import com.itstep.caloriesmanager.model.Role;
import com.itstep.caloriesmanager.model.User;
import com.itstep.caloriesmanager.repository.UserRepository;
import com.itstep.caloriesmanager.service.UserService;
import com.itstep.caloriesmanager.web.user.AdminRestController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ROLE_ADMIN));
        }
    }
}
