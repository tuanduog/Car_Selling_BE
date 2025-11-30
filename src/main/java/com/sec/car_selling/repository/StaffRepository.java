package com.sec.car_selling.repository;

import com.sec.car_selling.dto.response.StaffResponse;
import com.sec.car_selling.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

    @Query("""
        SELECT new com.sec.car_selling.dto.response.StaffResponse(s.id, s.code, u.fullName, u.email, s.phone, s.birthday, s.gender, s.address, s.managerId, u.role, u.status)
        FROM Staff s
        JOIN User u ON s.userId = u.id
        WHERE u.role = :role
            AND (:status IS NULL OR s.status = :status)
            AND (LOWER(u.email) LIKE :keyword OR LOWER(u.fullName) LIKE :keyword)
    """)
    Page<StaffResponse> findAllByKeywordAndStatus(Pageable pageable, String keyword, Integer status, String role);
}