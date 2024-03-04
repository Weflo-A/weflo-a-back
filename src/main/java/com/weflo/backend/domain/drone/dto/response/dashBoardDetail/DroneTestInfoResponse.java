package com.weflo.backend.domain.drone.dto.response.dashBoardDetail;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DroneTestInfoResponse {
    private String name;
    private String testDate;
    private String station;
}
