package com.weflo.backend.domain.drone.dto.response.dashBoardDetail;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DroneWarningResponse {
    private String part;
    private String component;
    private int score;
    public static DroneWarningResponse createDroneWarningResponse(String part, String component, int score){
        DroneWarningResponse droneWarningResponse = DroneWarningResponse.builder()
                .part(part)
                .component(component)
                .score(score)
                .build();
        return droneWarningResponse;
    }
}
