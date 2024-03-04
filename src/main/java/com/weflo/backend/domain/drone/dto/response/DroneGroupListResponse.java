package com.weflo.backend.domain.drone.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class DroneGroupListResponse {
    private String groupName;
    private List<DroneListResponse> droneList;
    public static DroneGroupListResponse of(String name, List<DroneListResponse> droneList){
        return DroneGroupListResponse.builder()
                .groupName(name)
                .droneList(droneList)
                .build();
    }
}
