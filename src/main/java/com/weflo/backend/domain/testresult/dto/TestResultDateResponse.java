package com.weflo.backend.domain.testresult.dto;

import com.weflo.backend.domain.testresult.domain.TestResult;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record TestResultDateResponse(
        int year,
        int month,
        int day
) {

    public static List<TestResultDateResponse> ofList(List<TestResult> testResults) {
        return testResults.stream().map(result -> builder()
                    .year(result.getCreateDate().getYear())
                    .month(result.getCreateDate().getMonthValue())
                    .day(result.getCreateDate().getDayOfMonth())
                    .build())
                .toList();
    }
}
