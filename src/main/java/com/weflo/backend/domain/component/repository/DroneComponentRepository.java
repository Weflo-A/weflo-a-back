package com.weflo.backend.domain.component.repository;

import com.weflo.backend.domain.component.domain.DroneComponent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneComponentRepository extends JpaRepository<DroneComponent, Long> {
}
