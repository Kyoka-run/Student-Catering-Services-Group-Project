package com.studentCatering.repository;

import java.util.Optional;

import com.studentCatering.entity.StoreOccupancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StoreOccupancyRepository extends JpaRepository<StoreOccupancy, Integer> {

    Optional<StoreOccupancy> findTopByOrderByIdDesc();
}