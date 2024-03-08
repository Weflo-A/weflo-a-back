package com.weflo.backend.domain.component.dto;

import com.weflo.backend.domain.component.domain.Component;
import com.weflo.backend.domain.component.domain.ComponentType;
import com.weflo.backend.domain.component.domain.Part;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;

@Builder
public record ComponentResponse(
        String image,
        ComponentType type,
        Part part,
        String name,
        Double star,
        String description,
        int price
) {

    public static ComponentResponse of(Component component) {
        return builder()
                .image(component.getImage())
                .type(component.getType())
                .part(component.getPart())
                .name(component.getName())
                .star(component.getStar())
                .description(component.getDescription())
                .price(component.getPrice())
                .build();
    }

    public static List<ComponentResponse> ofList(List<Component> droneComponents) {
        List<ComponentResponse> componentResponses = new ArrayList<>();

        for (Component component : droneComponents) {
            ComponentResponse componentResponse = builder()
                    .image(component.getImage())
                    .type(component.getType())
                    .part(component.getPart())
                    .name(component.getName())
                    .star(component.getStar())
                    .description(component.getDescription())
                    .price(component.getPrice())
                    .build();

            componentResponses.add(componentResponse);
        }

        return componentResponses;
    }
}
