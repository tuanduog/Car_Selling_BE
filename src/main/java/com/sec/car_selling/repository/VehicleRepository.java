package com.sec.car_selling.repository;

import com.sec.car_selling.dto.response.VehicleResponse;
import com.sec.car_selling.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    @Query(value = """
        SELECT new com.sec.car_selling.dto.response.VehicleResponse(v.id, v.code, v.name, v.version, v.price, v.releaseDate)
        FROM Vehicle v
        WHERE (LOWER(v.code) LIKE :keyword OR LOWER(v.name) LIKE :keyword)
        AND (v.status <> 2)
    """)
    Page<VehicleResponse> findAllByKeyword(Pageable pageable, String keyword);

    @Query(value = """
        SELECT v
        FROM Vehicle v
        WHERE v.code = :code
    """)
    Optional<Vehicle> findByCode(String code);
}
