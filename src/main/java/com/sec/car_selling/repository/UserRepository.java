package com.sec.car_selling.repository;

import com.sec.car_selling.dto.response.AccountResponse;
import com.sec.car_selling.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    @Query("""
        SELECT new com.sec.car_selling.dto.response.AccountResponse(u.id, u.fullName, u.email, u.role, u.createdAt, u.updatedAt, u.status)
        FROM User u
        WHERE (LOWER(u.fullName) LIKE :keyword)
            OR LOWER(u.email) LIKE :keyword
        AND (:status IS NULL OR u.status = :status)
        AND (:role IS NULL OR u.role = :role)
    """)
    Page<AccountResponse> findAllByKeywordAndStatus(Pageable pageable, String keyword, Integer status, String role);
}
