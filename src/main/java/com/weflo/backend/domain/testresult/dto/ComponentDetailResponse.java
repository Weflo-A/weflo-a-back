package com.weflo.backend.domain.testresult.dto;

import com.weflo.backend.domain.component.dto.DroneComponentResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.Builder;

@Builder
public record ComponentDetailResponse(
        String type,
        String part,
        Long score,
        Boolean warning
) {

    public static List<ComponentDetailResponse> ofList(List<DroneComponentResponse> droneComponents, int warningLimit, int responseLimit) {
        List<DroneComponentResponse> sortedDroneComponents = droneComponents.stream()
                .sorted(Comparator.comparing(DroneComponentResponse::getPoint)).toList();

        List<ComponentDetailResponse> result = new ArrayList<>();

        for (int i = 0; i < sortedDroneComponents.size(); i++) {
            DroneComponentResponse component = sortedDroneComponents.get(i);
            if (i < warningLimit) {
                ComponentDetailResponse response = builder().part(component.getPart())
                        .type(component.getType())
                        .score(component.getPoint())
                        .warning(true)
                        .build();

                result.add(response);
            }else {
                ComponentDetailResponse response = builder().part(component.getPart())
                        .type(component.getType())
                        .score(component.getPoint())
                        .warning(false)
                        .build();

                result.add(response);
            }
        }

        return result.subList(0, responseLimit);
    }
}
