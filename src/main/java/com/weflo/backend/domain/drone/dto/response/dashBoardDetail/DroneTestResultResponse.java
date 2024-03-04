package com.weflo.backend.domain.drone.dto.response.dashBoardDetail;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DroneTestResultResponse {
    private int totalScore;
    private int part1Score;
    private int part2Score;
    private int part3Score;
    private int part4Score;
    public static DroneTestResultResponse of(){
        return DroneTestResultResponse.builder()
                .totalScore()
                .part1Score()
                .part2Score()
                .part3Score()
                .part4Score()
                .build();
    }
}
