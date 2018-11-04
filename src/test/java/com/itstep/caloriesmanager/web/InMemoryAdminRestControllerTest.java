package com.itstep.caloriesmanager.web;

import com.itstep.caloriesmanager.UserTestData;
import com.itstep.caloriesmanager.model.User;
import com.itstep.caloriesmanager.repository.inmemory.InMemoryUserRepositoryImpl;
import com.itstep.caloriesmanager.util.exception.NotFoundException;
import com.itstep.caloriesmanager.web.user.AdminRestController;
import org.junit.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Collection;

import static com.itstep.caloriesmanager.UserTestData.ADMIN;

public class InMemoryAdminRestControllerTest {
    private static ConfigurableApplicationContext appCtx;
    private static AdminRestController controller;

    @BeforeClass
    public static void beforeClass() {
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/inmemory.xml");
        System.out.println("\n" + Arrays.toString(appCtx.getBeanDefinitionNames()) + "\n");
        controller = appCtx.getBean(AdminRestController.class);
    }

    @AfterClass
    public static void afterClass() {
        appCtx.close();
    }

    @Before
    public void setUp() throws Exception {
        // re-initialize
        InMemoryUserRepositoryImpl repository = appCtx.getBean(InMemoryUserRepositoryImpl.class);
        repository.init();
    }

    @Test
    public void delete() throws Exception {
        controller.delete(UserTestData.USER_ID);
        Collection<User> users = controller.getAll();
        Assert.assertEquals(users.size(), 1);
        Assert.assertEquals(users.iterator().next(), ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        controller.delete(10);
    }
}