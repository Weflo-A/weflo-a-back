package com.weflo.backend.domain.drone.dto.response.onBoarding;

import com.weflo.backend.domain.drone.domain.Drone;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SearchDroneResponse {
    String name;
    String model;
    int year;
    List<String> groupList;
    public static SearchDroneResponse of(Drone drone, List<String> groupList ){
        return SearchDroneResponse.builder()
                .name(drone.getName())
                .model(String.valueOf(drone.getModel()))
                .year(drone.getProductionYear().getYear())
                .groupList(groupList)
                .build();
    }
}
