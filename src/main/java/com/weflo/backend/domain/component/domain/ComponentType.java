package com.weflo.backend.domain.component.domain;

import lombok.Getter;

@Getter
public enum ComponentType {
    BLADE("블레이드"),
    MOTOR("모터"),
    ESC("ESC"),
    OTHER("기타");

    final String koreanName;

    ComponentType(String koreanName) {
        this.koreanName = koreanName;
    }

    public static ComponentType findTypeByName(String name) {
        if (BLADE.name().equals(name)) {
            return BLADE;
        }

        if (MOTOR.name().equals(name)) {
            return MOTOR;
        }

        if (ESC.name().equals(name)) {
            return ESC;
        }

        return null;
    }
}
