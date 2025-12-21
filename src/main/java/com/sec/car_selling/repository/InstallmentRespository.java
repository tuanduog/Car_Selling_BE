package com.sec.car_selling.repository;

import com.sec.car_selling.entity.Installment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallmentRespository extends JpaRepository<Installment, Integer> {
}
