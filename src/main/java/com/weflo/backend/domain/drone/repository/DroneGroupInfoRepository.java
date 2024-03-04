package com.weflo.backend.domain.drone.repository;

import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.domain.DroneGroup;
import com.weflo.backend.domain.drone.domain.DroneGroupInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneGroupInfoRepository extends JpaRepository<DroneGroupInfo, Long> {
    DroneGroup findDroneGroupByDroneId(Long droneId);
    List<Drone> findAllDroneByDroneGroupId(Long DroneGroupId);
}
