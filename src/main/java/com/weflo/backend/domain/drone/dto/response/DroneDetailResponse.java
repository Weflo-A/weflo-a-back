package com.weflo.backend.domain.drone.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder
@Getter
public class DroneDetailResponse {
    private DroneInfoResponse droneInfo;
    private List<TimeLineResponse> timeLine;
    private List<TestListResponse> testList;
//    private BrokenTypeResponse brokenTypeResponse;
    private List<GroupListResponse> groupList;

    public static DroneDetailResponse of(DroneInfoResponse droneInfo,List<TimeLineResponse> timeLine, List<TestListResponse> testList,List<GroupListResponse> groupList ){
        return DroneDetailResponse.builder()
                .droneInfo(droneInfo)
                .timeLine(timeLine)
                .testList(testList)
                .groupList(groupList)
                .build();
    }
}
