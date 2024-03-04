package com.weflo.backend.domain.drone.repository;

import com.weflo.backend.domain.drone.domain.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, Long> {
}
