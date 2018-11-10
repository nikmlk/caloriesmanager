package com.itstep.caloriesmanager.repository.datajpa;

import com.itstep.caloriesmanager.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
}
