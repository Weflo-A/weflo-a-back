package com.weflo.backend.domain.drone.dto.response.dashBoardDetail;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DroneTotalScoreResponse {
    private TotalScoreResponse totalScore;
    private String part;
    private String component;
    private String date;
    private int balance;
    private int accident;
    public static DroneTotalScoreResponse of(TotalScoreResponse totalScore){
        return DroneTotalScoreResponse.builder()
                .totalScore(totalScore)
                .part("더미")
                .component("더미")
                .date("더미")
                .balance(11)
                .accident(11)
                .build();
    }
}
