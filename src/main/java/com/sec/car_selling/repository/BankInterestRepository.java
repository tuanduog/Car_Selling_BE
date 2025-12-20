package com.sec.car_selling.repository;

import com.sec.car_selling.dto.response.BankInterestResponse;
import com.sec.car_selling.entity.BankInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankInterestRepository extends JpaRepository<BankInterest, Integer> {

    @Query(value = """
        SELECT new com.sec.car_selling.dto.response.BankInterestResponse(ir.id, ir.bank, ir.interestRate)
        FROM BankInterest ir
        WHERE ir.status = 1
    """)
    List<BankInterestResponse> getList();
}
