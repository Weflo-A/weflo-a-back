package com.weflo.backend.domain.drone.dto.response.dashBoardDetail;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DroneScoreResponse {
    private int motor;
    private int blade;
    private int esc;
    private int total;
}
