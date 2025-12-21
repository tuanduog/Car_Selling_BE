package com.sec.car_selling.repository;

import com.sec.car_selling.dto.response.PaymentResponse;
import com.sec.car_selling.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {

    @Query(value = """
        SELECT new com.sec.car_selling.dto.response.PaymentResponse(p.id, p.code, c.paymentName, p.carName, p.price, p.type, p.createdAt, p.paymentStatus)
        FROM Payment p
        JOIN Customer c ON p.customerId = c.id
        WHERE (LOWER(p.code) LIKE :keyword OR LOWER(c.paymentName) LIKE :keyword)
            AND (:paymentStatus IS NULL OR p.paymentStatus = :paymentStatus)
    """)
    Page<PaymentResponse> findAllByKeyword(Pageable pageable, String keyword, Integer paymentStatus);
}
