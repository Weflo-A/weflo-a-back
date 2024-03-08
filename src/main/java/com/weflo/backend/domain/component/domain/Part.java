package com.weflo.backend.domain.component.domain;

import lombok.Getter;

public enum Part {
    PART1("구동부 1"),
    PART2("구동부 2"),
    PART3("구동부 3"),
    PART4("구동부 4");

    final String koreanName;

    Part(String koreanName) {
        this.koreanName = koreanName;
    }
}
