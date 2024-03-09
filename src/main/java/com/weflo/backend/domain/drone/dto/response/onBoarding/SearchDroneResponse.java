package com.weflo.backend.domain.drone.dto.response.onBoarding;

import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.dto.response.DroneGroupResponse;
import com.weflo.backend.domain.drone.dto.response.DroneListResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SearchDroneResponse {
    List<DroneGroupResponse> groupInfo;
    List<SearchDroneListResponse> droneInfo;
    public static SearchDroneResponse of(List<DroneGroupResponse> groupInfo, List<SearchDroneListResponse> droneInfo ){
        return SearchDroneResponse.builder()
                .groupInfo(groupInfo)
                .droneInfo(droneInfo)
                .build();
    }
}
