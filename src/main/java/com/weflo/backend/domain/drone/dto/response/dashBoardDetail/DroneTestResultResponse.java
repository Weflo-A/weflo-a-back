package com.weflo.backend.domain.drone.dto.response.dashBoardDetail;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class DroneTestResultResponse {
    private int totalScore;
    private TotalScoreResponse totalScoreResponse;
    private List<DroneScoreResponse> droneScoreResponse;
    public static DroneTestResultResponse of(TotalScoreResponse totalScoreResponse,int totalScore, List<DroneScoreResponse> droneScoreResponse){
        return DroneTestResultResponse.builder()
                .totalScore(totalScore)
                .totalScoreResponse(totalScoreResponse)
                .droneScoreResponse(droneScoreResponse)
                .build();
    }
}
