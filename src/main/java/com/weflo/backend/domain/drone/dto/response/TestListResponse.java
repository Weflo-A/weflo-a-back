package com.weflo.backend.domain.drone.dto.response;

import com.weflo.backend.domain.testresult.domain.TestResult;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TestListResponse {
    private String testDate;
    private String space;
    private int point;

    public static TestListResponse of(TestResult testResult, int point){
        return TestListResponse.builder()
                .testDate(String.valueOf(testResult.getCreateDate()))
                .space(testResult.getSpace())
                .point(point)
                .build();
    }
}
