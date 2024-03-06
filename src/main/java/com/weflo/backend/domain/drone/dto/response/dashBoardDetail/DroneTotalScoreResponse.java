package com.weflo.backend.domain.drone.dto.response.dashBoardDetail;

import com.weflo.backend.domain.testresult.domain.TestResult;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Builder
@Getter
public class DroneTotalScoreResponse {
    private int accident;
    private String expectedDate;
    private String exchangeDate;

    public static DroneTotalScoreResponse of(TestResult testResult, TestResult closetTestResult){
        return DroneTotalScoreResponse.builder()
                .accident(11)
                .expectedDate(testResult.getExpectedDate().format(DateTimeFormatter.ofPattern("MM월 dd일")))
                .exchangeDate(closetTestResult.getCreateDate().format(DateTimeFormatter.ofPattern("MM월 dd일")))
                .build();
    }
}
