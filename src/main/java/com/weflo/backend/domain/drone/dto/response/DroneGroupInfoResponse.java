package com.weflo.backend.domain.drone.dto.response;

import com.weflo.backend.domain.drone.domain.DroneGroup;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DroneGroupInfoResponse {
    private Long groupId;
    private String name;
    public static DroneGroupInfoResponse of(DroneGroup droneGroup){
        return DroneGroupInfoResponse.builder()
                .groupId(droneGroup.getId())
                .name(droneGroup.getName())
                .build();
    }
}
