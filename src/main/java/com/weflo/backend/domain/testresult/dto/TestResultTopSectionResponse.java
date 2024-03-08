package com.weflo.backend.domain.testresult.dto;

import com.weflo.backend.domain.drone.dto.response.dashBoardDetail.TotalScoreResponse;
import java.util.List;
import lombok.Builder;

@Builder
public record TestResultTopSectionResponse(
        PartTotalScoreResponse totalScore,
        List<ComponentDetailResponse> components

) {
}
