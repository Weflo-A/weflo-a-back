package com.weflo.backend.domain.drone.domain;

import com.weflo.backend.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "drone_group_info")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public class DroneGroupInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drone_group_info_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drone_id")
    private Drone drone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drone_group_id")
    private DroneGroup droneGroup;

    public static DroneGroupInfo createDroneGroupInfo(DroneGroup droneGroup, Drone drone) {
            DroneGroupInfo droneGroupInfo = DroneGroupInfo.builder()
                    .drone(drone)
                    .droneGroup(droneGroup)
                    .build();
            droneGroup.addDroneGroupInfo(droneGroupInfo);
        return droneGroupInfo;
    }
}
