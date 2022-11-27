package com.spring.springselenium.Repository;

import com.spring.springselenium.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByFirstNameStartingWith(String startsWith);
    List<Customer> findByDobBetween(Date from, Date to);
}
