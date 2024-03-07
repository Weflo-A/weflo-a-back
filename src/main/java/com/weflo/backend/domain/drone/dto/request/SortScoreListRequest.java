package com.weflo.backend.domain.drone.dto.request;

import lombok.Getter;

@Getter
public class SortScoreListRequest {
    private Long droneId;
    private String date;
    private String filter;
}
