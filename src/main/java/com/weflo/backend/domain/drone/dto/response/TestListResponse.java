package com.weflo.backend.domain.drone.dto.response;

import com.weflo.backend.domain.testresult.domain.TestResult;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Builder
@Getter
public class TestListResponse {
    private Long testResultId;
    private String testDate;
    private String space;
    private int point;

    public static TestListResponse of(TestResult testResult, int point){
        return TestListResponse.builder()
                .testResultId(testResult.getId())
                .testDate(testResult.getCreateDate().format(DateTimeFormatter.ofPattern("yy.MM.dd")))
                .space(testResult.getSpace())
                .point(point)
                .build();
    }
}
