package com.weflo.backend.domain.drone.dto.response.onBoarding;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DroneGroupStateResponse {
    private int avgScore;
    private String brokenType;
    private String mostFixComponent;
    public static DroneGroupStateResponse of(int avgScore){
        return DroneGroupStateResponse.builder()
                .avgScore(avgScore)
                .brokenType("블레이드")
                .mostFixComponent("블레이드 210");
    }
}
