package com.weflo.backend.domain.component.domain;

public enum ComponentType {
    BLADE, MOTOR, ESC;

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
