package com.weflo.backend.domain.drone.repository;

import com.weflo.backend.domain.drone.domain.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, Long> {
    List<Drone> findAllByNameContaining(String Name);
}
