package com.weflo.backend.domain.component.repository;

import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.domain.DroneComponent;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneComponentRepository extends JpaRepository<DroneComponent, Long> {
    List<DroneComponent> findByDroneIdAndPointGreaterThanEqual(Long droneId, Long point);
    List<DroneComponent> findByDroneIdAndPointLessThanEqual(Long droneId, Long point);
}
