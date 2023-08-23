package com.example.jpademo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpademo.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
