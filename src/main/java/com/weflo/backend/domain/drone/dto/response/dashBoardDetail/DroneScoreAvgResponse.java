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
    public static DroneScoreAvgResponse of(int motorAvg, int bladeAvg, int escAvg, int totalAvg){
        return DroneScoreAvgResponse.builder()
                .motorAvg(motorAvg)
                .bladeAvg(bladeAvg)
                .escAvg(escAvg)
                .totalAvg(totalAvg)
                .build();
    }
}
