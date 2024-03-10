package com.weflo.backend.domain.drone.dto.response;

import com.weflo.backend.domain.drone.domain.DroneGroup;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DroneGroupResponse {
    private String groupId;
    private String name;
    public static DroneGroupResponse of(DroneGroup droneGroup){
        return DroneGroupResponse.builder()
                .groupId(String.valueOf(droneGroup.getId()))
                .name(droneGroup.getName())
                .build();
    }
    public static DroneGroupResponse create(String groupId,String name){
        return DroneGroupResponse.builder()
                .groupId(groupId)
                .name(name)
                .build();
    }
}
