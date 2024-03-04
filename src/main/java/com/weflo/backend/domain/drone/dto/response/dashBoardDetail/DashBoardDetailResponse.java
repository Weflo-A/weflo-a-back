package com.weflo.backend.domain.drone.dto.response.dashBoardDetail;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

//상세 대시보드 화면 정의서 드론 정보(1)
@Builder
@Getter
public class DashBoardDetailResponse {
    private DroneTestInfoResponse testInfo;
    private DroneTestResultResponse testResult;
    private List<DroneScoreResponse> scoreList;
    private DroneScoreAvgResponse scoreAvg;
    private DroneTotalScoreResponse totalScore;
    private List<DroneWarningResponse> warningList;
    private String warningPart;
}
