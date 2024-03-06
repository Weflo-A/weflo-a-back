package com.weflo.backend.domain.drone.dto.response.onBoarding;

import com.weflo.backend.domain.cost.dto.ComponentCostAvgTimeLine;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class DroneGroupInfoResponse {
    private DroneGroupInfoDetailResponse droneGroupInfoDetailResponse;
    private List<ComponentCostAvgTimeLine> componentCostAvgTimeLines;
    public static DroneGroupInfoResponse of(DroneGroupInfoDetailResponse droneGroupInfoDetailResponse, List<ComponentCostAvgTimeLine> componentCostAvgTimeLines){
        return DroneGroupInfoResponse.builder()
                .droneGroupInfoDetailResponse(droneGroupInfoDetailResponse)
                .componentCostAvgTimeLines(componentCostAvgTimeLines)
                .build();
    }
}
