package com.weflo.backend.domain.drone.dto.response.onBoarding;

import com.weflo.backend.domain.cost.dto.ComponentCostAvgTimeLine;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class DroneGroupInfoResponse {
    private DroneGroupInfoDetailResponse groupInfoDetail;
    private List<ComponentCostAvgTimeLine> costAvgTimeLines;
    public static DroneGroupInfoResponse of(DroneGroupInfoDetailResponse droneGroupInfoDetailResponse, List<ComponentCostAvgTimeLine> componentCostAvgTimeLines){
        return DroneGroupInfoResponse.builder()
                .groupInfoDetail(droneGroupInfoDetailResponse)
                .costAvgTimeLines(componentCostAvgTimeLines)
                .build();
    }
}
