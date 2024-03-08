package com.weflo.backend.domain.testresult.dto;

import lombok.Builder;

@Builder
public record PartTotalScoreResponse(
        int part1,
        int part2,
        int part3,
        int part4
) {
}
