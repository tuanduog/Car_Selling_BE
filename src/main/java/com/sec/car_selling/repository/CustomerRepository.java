package com.sec.car_selling.repository;

import com.sec.car_selling.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Query(value = """
        SELECT c
            FROM Customer c
            WHERE c.userId = :id
    """)
    List<Customer> findAllByUserId(Integer id);
}
