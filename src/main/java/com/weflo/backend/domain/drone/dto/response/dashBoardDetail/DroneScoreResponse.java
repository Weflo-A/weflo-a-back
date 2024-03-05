package com.weflo.backend.domain.drone.dto.response.dashBoardDetail;

import com.weflo.backend.domain.testresult.domain.TestResult;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DroneScoreResponse {
    private int motor;
    private int blade;
    private int esc;
    private int total;
    public static DroneScoreResponse createDroneScoreResponse(int motor, int blade, int esc, int total){
        DroneScoreResponse droneScoreResponse = DroneScoreResponse.builder()
                .motor(motor)
                .blade(blade)
                .esc(esc)
                .total(total)
                .build();
        return droneScoreResponse;
    }
}
