package com.weflo.backend.domain.drone.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder
@Getter
public class DroneDetailResponse {
    private DroneGroupListResponse droneGroup;
    private int totalScore;
    private DroneInfoResponse droneInfo;
    private List<TimeLineResponse> timeLine;
    private List<TestListResponse> testList;
//    private BrokenTypeResponse brokenTypeResponse;

    public static DroneDetailResponse of(DroneInfoResponse droneInfo,List<TimeLineResponse> timeLine, List<TestListResponse> testList, DroneGroupListResponse droneGroupListResponse, int cost ){
        return DroneDetailResponse.builder()
                .droneInfo(droneInfo)
                .timeLine(timeLine)
                .testList(testList)
                .totalScore(cost)
                .droneGroup(droneGroupListResponse)
                .build();
    }
}
