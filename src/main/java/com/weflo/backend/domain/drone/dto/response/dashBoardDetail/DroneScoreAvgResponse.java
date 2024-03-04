package com.weflo.backend.domain.drone.dto.response.dashBoardDetail;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DroneScoreAvgResponse {
    private int motorAvg;
    private int bladeAvg;
    private int escAvg;
    private int totalAvg;
}
