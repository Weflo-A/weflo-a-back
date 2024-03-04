package com.weflo.backend.domain.drone.repository;

import com.weflo.backend.domain.drone.domain.DroneComponent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneComponentRepository extends JpaRepository<DroneComponent, Long> {
}
