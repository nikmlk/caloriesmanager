package com.itstep.caloriesmanager.service.datajpa;

import com.itstep.caloriesmanager.MealTestData;
import com.itstep.caloriesmanager.model.User;
import com.itstep.caloriesmanager.service.AbstractJpaUserServiceTest;
import com.itstep.caloriesmanager.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import static com.itstep.caloriesmanager.Profiles.DATAJPA;
import static com.itstep.caloriesmanager.UserTestData.*;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends AbstractJpaUserServiceTest {
    @Test
    public void testGetWithMeals() throws Exception {
        User user = service.getWithMeals(USER_ID);
        assertMatch(user, USER);
        MealTestData.assertMatch(user.getMeals(), MealTestData.MEALS);
    }

    @Test(expected = NotFoundException.class)
    public void testGetWithMealsNotFound() throws Exception {
        service.getWithMeals(1);
    }
}