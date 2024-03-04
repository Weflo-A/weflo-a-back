package com.weflo.backend.domain.drone.dto.response.dashBoardDetail;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DroneTotalScoreResponse {
    private TotalScoreResponse totalScore;
    private String part;
    private String date;
    private int balance;
    private int accident;
}
