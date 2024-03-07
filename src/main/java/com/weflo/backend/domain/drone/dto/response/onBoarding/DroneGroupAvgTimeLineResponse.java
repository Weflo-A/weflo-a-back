package com.weflo.backend.domain.drone.dto.response.onBoarding;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DroneGroupAvgTimeLineResponse {
    private int month;
    private int avgScore;
    public static DroneGroupAvgTimeLineResponse of(int month, int avgScore){
        return DroneGroupAvgTimeLineResponse.builder()
                .month(month)
                .avgScore(avgScore)
                .build();
    }
}
