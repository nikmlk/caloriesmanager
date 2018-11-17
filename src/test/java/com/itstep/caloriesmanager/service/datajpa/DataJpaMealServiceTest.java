package com.itstep.caloriesmanager.service.datajpa;

import com.itstep.caloriesmanager.service.AbstractMealServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static com.itstep.caloriesmanager.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
public class DataJpaMealServiceTest extends AbstractMealServiceTest {
}
