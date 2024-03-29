package com.weflo.backend.domain.drone.domain;

import com.weflo.backend.domain.drone.dto.request.CreateDroneGroupRequest;
import com.weflo.backend.domain.drone.dto.request.CreateDroneRequest;
import com.weflo.backend.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drone_group")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public class DroneGroup extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drone_group_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "droneGroup")
    @Builder.Default
    private List<DroneGroupInfo> droneGroupInfos = new ArrayList<>();

    public void addDroneGroupInfo(DroneGroupInfo droneGroupInfo) {
        this.droneGroupInfos.add(droneGroupInfo);
    }
    public static DroneGroup createDroneGroup(CreateDroneGroupRequest createDroneGroupRequest) {
        DroneGroup droneGroup = DroneGroup.builder()
                .name(createDroneGroupRequest.getGroupName())
                .build();
        return droneGroup;
    }
}
