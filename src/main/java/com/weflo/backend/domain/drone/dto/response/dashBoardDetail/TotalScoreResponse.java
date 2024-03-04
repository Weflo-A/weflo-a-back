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
    public static TotalScoreResponse of(int part1Avg, int part2Avg, int part3Avg, int part4Avg){
        return TotalScoreResponse.builder()
                .part1Avg(part1Avg)
                .part2Avg(part2Avg)
                .part3Avg(part3Avg)
                .part4Avg(part4Avg)
                .build();
    }
}
