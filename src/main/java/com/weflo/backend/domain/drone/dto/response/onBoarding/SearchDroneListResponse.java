package com.weflo.backend.domain.drone.dto.response.onBoarding;

import com.weflo.backend.domain.drone.domain.Drone;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SearchDroneListResponse {
    Long droneId;
    String name;
    String model;
    int year;
    List<String> groupList;
    public static SearchDroneListResponse of(Drone drone,List<String> groupList){
        return SearchDroneListResponse.builder()
                .droneId(drone.getId())
                .name(drone.getName())
                .model(String.valueOf(drone.getModel()))
                .year(drone.getProductionYear())
                .groupList(groupList)
                .build();
    }

}
