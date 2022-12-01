package com.spring.springselenium.StepDefinitions;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Student,Integer> {
}
