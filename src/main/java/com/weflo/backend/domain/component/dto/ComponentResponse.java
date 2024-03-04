package com.weflo.backend.domain.component.dto;

import com.weflo.backend.domain.component.domain.Component;
import com.weflo.backend.domain.component.domain.ComponentType;
import com.weflo.backend.domain.component.domain.Part;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

public class ComponentResponse{

    @Builder
    @Getter
    public static class ExchangeComponentResponse {
        private final String image;
        private final ComponentType type;
        private final Part part;
        private final String name;
        private final String description;
        private final int price;

        public static List<ExchangeComponentResponse> ofList(List<Component> allComponents) {
            return allComponents.stream().map(component -> builder()
                    .image(component.getImage())
                    .type(component.getType())
                    .part(component.getPart())
                    .name(component.getName())
                    .description(component.getDescription())
                    .price(component.getPrice())
                    .build()
            ).toList();
        }
    }



}
