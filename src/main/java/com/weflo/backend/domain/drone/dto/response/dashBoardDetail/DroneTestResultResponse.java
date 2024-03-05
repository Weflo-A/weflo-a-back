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
    public static DroneTestResultResponse of(int totalScore, int part1Score, int part2Score, int part3Score, int part4Score){
        return DroneTestResultResponse.builder()
                .totalScore(totalScore)
                .part1Score(part1Score)
                .part2Score(part2Score)
                .part3Score(part3Score)
                .part4Score(part4Score)
                .build();
    }
}
