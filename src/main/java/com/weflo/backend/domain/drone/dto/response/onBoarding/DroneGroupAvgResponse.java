package com.weflo.backend.domain.drone.dto.response.onBoarding;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class DroneGroupAvgResponse {
    private DroneGroupStateResponse groupState;
    private List<DroneGroupAvgTimeLineResponse> groupAvgList;
    public static DroneGroupAvgResponse of(DroneGroupStateResponse groupState, List<DroneGroupAvgTimeLineResponse> groupAvgList){
        return DroneGroupAvgResponse.builder()
                .groupState(groupState)
                .groupAvgList(groupAvgList)
                .build();
    }
}
