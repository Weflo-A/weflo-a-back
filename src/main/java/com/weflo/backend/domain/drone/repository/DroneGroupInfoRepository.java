package com.weflo.backend.domain.drone.repository;

import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.domain.DroneGroup;
import com.weflo.backend.domain.drone.domain.DroneGroupInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DroneGroupInfoRepository extends JpaRepository<DroneGroupInfo, Long> {
    @Query("SELECT dg FROM DroneGroupInfo dgi " +
            "JOIN dgi.droneGroup dg " +
            "WHERE dgi.drone.id = :droneId " +
            "ORDER BY dgi.createDate DESC " +
            "LIMIT 1")
    DroneGroup findTopByDroneIdOrderByCreateDateDesc(@Param("droneId") Long droneId);
    @Query("SELECT dgi.drone FROM DroneGroupInfo dgi WHERE dgi.droneGroup.id = :droneGroupId")
    List<Drone> findAllDroneByDroneGroupId(@Param("droneGroupId")Long droneGroupId);
    @Query("SELECT dgi.droneGroup FROM DroneGroupInfo dgi WHERE dgi.drone.id = :droneId")
    List<DroneGroup> findAllDroneGroupByDroneId(@Param("droneId") Long droneId);
}
