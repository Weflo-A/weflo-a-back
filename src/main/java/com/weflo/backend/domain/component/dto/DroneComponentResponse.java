package com.weflo.backend.domain.component.dto;

import com.weflo.backend.domain.component.domain.Component;
import com.weflo.backend.domain.component.domain.ComponentType;
import com.weflo.backend.domain.component.domain.Part;
import com.weflo.backend.domain.drone.domain.DroneComponent;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DroneComponentResponse{
    private String image;
    private ComponentType type;
    private Part part;
    private String name;
    private Long point;
    private Double star;
    private String description;
    private int price;

    public static List<DroneComponentResponse> ofList(List<DroneComponent> droneComponents) {
        List<DroneComponentResponse> droneComponentResult = new ArrayList<>();

        for (DroneComponent droneComponent : droneComponents) {
            Component component = droneComponent.getComponent();
            DroneComponentResponse droneComponentResponse = builder()
                    .image(component.getImage())
                    .type(component.getType())
                    .part(component.getPart())
                    .point(droneComponent.getPoint())
                    .name(component.getName())
                    .star(component.getStar())
                    .description(component.getDescription())
                    .price(component.getPrice())
                    .build();

            droneComponentResult.add(droneComponentResponse);
        }

        return droneComponentResult;
    }
}
