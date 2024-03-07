package com.weflo.backend.domain.drone.dto.response.onBoarding;

import com.weflo.backend.domain.drone.domain.DroneGroup;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DroneGroupNameResponse {
    private Long groupId;
    private String name;
    public static DroneGroupNameResponse of(DroneGroup droneGroup){
        return DroneGroupNameResponse.builder()
                .groupId(droneGroup.getId())
                .name(droneGroup.getName())
                .build();
    }

}
