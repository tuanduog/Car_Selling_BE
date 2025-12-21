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
            AND (:paymentType IS NULL OR p.type = :paymentType)
    """)
    Page<PaymentResponse> findAllByKeyword(Pageable pageable, String keyword, Integer paymentStatus, Integer paymentType);

    @Query(value = """
        SELECT new com.sec.car_selling.dto.response.PaymentResponse(p.id, p.code, c.paymentName, p.carName, p.price, p.type, p.createdAt, p.paymentStatus, p.carVersion
            , p.carColor, c.paymentEmail, c.phone, c.identityNumber, s.city, s.name, i.loanDuration, i.downPayment, b.bank, b.interestRate)
        FROM Payment p
        JOIN Customer c ON p.customerId = c.id
        JOIN Showroom s ON p.showRoomId = s.id
        JOIN Installment i ON i.paymentId = p.id
        JOIN BankInterest b ON b.id = i.bankId
        WHERE p.id = :id
    """)
    PaymentResponse findByIdForInstallment(int id);

    @Query(value = """
        SELECT new com.sec.car_selling.dto.response.PaymentResponse(p.id, p.code, c.paymentName, p.carName, p.price, p.type, p.createdAt, p.paymentStatus, p.carVersion
            , p.carColor, c.paymentEmail, c.phone, c.identityNumber, s.city, s.name)
        FROM Payment p
        JOIN Customer c ON p.customerId = c.id
        JOIN Showroom s ON p.showRoomId = s.id
        WHERE p.id = :id
    """)
    PaymentResponse findByIdForFull(int id);
}
