package com.itstep.caloriesmanager.service.jdbc;

import com.itstep.caloriesmanager.service.AbstractUserServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static com.itstep.caloriesmanager.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {
}