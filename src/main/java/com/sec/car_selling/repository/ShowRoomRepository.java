package com.sec.car_selling.repository;

import com.sec.car_selling.entity.Showroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRoomRepository extends JpaRepository<Showroom, Integer> {
}
