package com.itstep.caloriesmanager.service.datajpa;

import com.itstep.caloriesmanager.service.AbstractUserServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static com.itstep.caloriesmanager.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {
}