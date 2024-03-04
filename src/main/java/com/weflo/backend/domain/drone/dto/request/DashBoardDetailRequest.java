package com.weflo.backend.domain.drone.dto.request;

import lombok.Getter;

@Getter
public class DashBoardDetailRequest {
    private Long droneId;
    private String date;
}
