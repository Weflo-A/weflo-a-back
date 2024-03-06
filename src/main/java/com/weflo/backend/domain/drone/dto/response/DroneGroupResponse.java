package com.weflo.backend.domain.drone.dto.response;

import com.weflo.backend.domain.drone.domain.DroneGroup;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DroneGroupResponse {
    private Long groupId;
    private String name;
    public static DroneGroupResponse of(DroneGroup droneGroup){
        return DroneGroupResponse.builder()
                .groupId(droneGroup.getId())
                .name(droneGroup.getName())
                .build();
    }
}
