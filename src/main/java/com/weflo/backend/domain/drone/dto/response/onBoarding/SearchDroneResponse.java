package com.weflo.backend.domain.drone.dto.response.onBoarding;

import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.dto.response.DroneGroupResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SearchDroneResponse {
    List<DroneGroupResponse> groupInfo;
    String name;
    String model;
    int year;
    List<String> groupList;
    public static SearchDroneResponse of(List<DroneGroupResponse> groupInfo, Drone drone, List<String> groupList ){
        return SearchDroneResponse.builder()
                .groupInfo(groupInfo)
                .name(drone.getName())
                .model(String.valueOf(drone.getModel()))
                .year(drone.getProductionYear().getYear())
                .groupList(groupList)
                .build();
    }
}
