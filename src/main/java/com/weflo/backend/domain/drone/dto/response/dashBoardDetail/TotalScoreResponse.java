package com.weflo.backend.domain.drone.dto.response.dashBoardDetail;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TotalScoreResponse {
    private int part1Avg;
    private int part2Avg;
    private int part3Avg;
    private int part4Avg;
}
