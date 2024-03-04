package com.weflo.backend.domain.drone.repository;

import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.domain.DroneGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneGroupRepository extends JpaRepository<DroneGroup, Long> {
}
