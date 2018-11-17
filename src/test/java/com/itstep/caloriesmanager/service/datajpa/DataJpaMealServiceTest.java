package com.itstep.caloriesmanager.service.datajpa;

import com.itstep.caloriesmanager.UserTestData;
import com.itstep.caloriesmanager.model.Meal;
import com.itstep.caloriesmanager.service.AbstractMealServiceTest;
import com.itstep.caloriesmanager.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import static com.itstep.caloriesmanager.MealTestData.*;
import static com.itstep.caloriesmanager.Profiles.DATAJPA;
import static com.itstep.caloriesmanager.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
public class DataJpaMealServiceTest extends AbstractMealServiceTest {
    @Test
    public void testGetWithUser() throws Exception {
        Meal adminMeal = service.getWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        assertMatch(adminMeal, ADMIN_MEAL1);
        UserTestData.assertMatch(adminMeal.getUser(), UserTestData.ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void testGetWithUserNotFound() throws Exception {
        service.getWithUser(MEAL1_ID, ADMIN_ID);
    }
}
