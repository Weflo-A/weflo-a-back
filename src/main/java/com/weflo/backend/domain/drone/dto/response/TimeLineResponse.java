package com.weflo.backend.domain.drone.dto.response;

import com.weflo.backend.domain.testresult.domain.TestResult;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Builder
@Getter
public class TimeLineResponse {
    private String date;
    private int escPoint;
    private int bladePoint;
    private int motorPoint;
    public static TimeLineResponse of(TestResult testResult, int escPoint, int bladePoint, int motorPoint){
        return TimeLineResponse.builder()
                .date(String.valueOf(testResult.getCreateDate().format(DateTimeFormatter.ofPattern("yy.MM.dd"))))
                .escPoint(escPoint)
                .bladePoint(bladePoint)
                .motorPoint(motorPoint)
                .build();
    }

}
