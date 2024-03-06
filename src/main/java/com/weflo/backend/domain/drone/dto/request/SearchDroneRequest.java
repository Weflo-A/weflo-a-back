package com.weflo.backend.domain.drone.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class SearchDroneRequest {
    String name;
    List<String> model;
    List<Integer> year;
    List<String> group;
}
