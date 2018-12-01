package com.itstep.caloriesmanager.service.datajpa;

import com.itstep.caloriesmanager.MealTestData;
import com.itstep.caloriesmanager.model.User;
import com.itstep.caloriesmanager.service.AbstractJpaUserServiceTest;
import com.itstep.caloriesmanager.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static com.itstep.caloriesmanager.Profiles.DATAJPA;
import static com.itstep.caloriesmanager.UserTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles(DATAJPA)
 class DataJpaUserServiceTest extends AbstractJpaUserServiceTest {
    @Test
    void testGetWithMeals() throws Exception {
        User admin = service.getWithMeals(ADMIN_ID);
        assertMatch(admin, ADMIN);
        MealTestData.assertMatch(admin.getMeals(), MealTestData.ADMIN_MEAL2, MealTestData.ADMIN_MEAL1);
    }

    @Test
    void testGetWithMealsNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.getWithMeals(1));
    }
}