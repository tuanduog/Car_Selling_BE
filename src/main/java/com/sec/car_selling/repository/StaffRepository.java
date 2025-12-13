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
        SELECT new com.sec.car_selling.dto.response.StaffResponse(s.id, s.code, u.fullName, u.email, s.phone, s.birthday, s.gender, s.address, u.role, u.status)
        FROM Staff s
        JOIN User u ON s.userId = u.id
        WHERE u.role = :role
            AND (:status IS NULL OR u.status = :status)
            AND (LOWER(u.email) LIKE :keyword OR LOWER(u.fullName) LIKE :keyword)
            AND (u.is_deleted IS NULL OR u.is_deleted = false)
    """)
    Page<StaffResponse> findAllLeaderByKeywordAndStatus(Pageable pageable, String keyword, Integer status, String role);

    @Query("""
        SELECT new com.sec.car_selling.dto.response.StaffResponse(s.id, s.code, u.fullName, u.email, s.phone, s.birthday, s.gender, s.address, s.managerId, m.code, mu.fullName, u.role, u.status)
        FROM Staff s
        JOIN User u ON s.userId = u.id
        LEFT JOIN Staff m ON s.managerId = m.id
        LEFT JOIN User mu ON m.userId = mu.id
        WHERE u.role = :role
            AND (:status IS NULL OR u.status = :status)
            AND (LOWER(u.email) LIKE :keyword OR LOWER(u.fullName) LIKE :keyword)
            AND (u.is_deleted IS NULL OR u.is_deleted = false)
    """)
    Page<StaffResponse> findAllStaffByKeywordAndStatus(Pageable pageable, String keyword, Integer status, String role);
}