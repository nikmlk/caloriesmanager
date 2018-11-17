package com.itstep.caloriesmanager.service.jpa;

import com.itstep.caloriesmanager.service.AbstractUserServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static com.itstep.caloriesmanager.Profiles.JPA;

@ActiveProfiles(JPA)
public class JpaUserServiceTest extends AbstractUserServiceTest {
}