package com.weflo.backend.domain.drone.dto.response.dashBoardDetail;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DroneScoreAvgResponse {
    private String type;
    private int score;
    public static DroneScoreAvgResponse createDroneScoreAvg(String type, int score){
        return DroneScoreAvgResponse.builder()
                .type(type)
                .score(score)
                .build();
    }
//    public static DroneScoreAvgResponse of(int motorAvg, int bladeAvg, int escAvg, int totalAvg){
//        return DroneScoreAvgResponse.builder()
//                .motorAvg(motorAvg)
//                .bladeAvg(bladeAvg)
//                .escAvg(escAvg)
//                .totalAvg(totalAvg)
//                .build();
//    }
}
