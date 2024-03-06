package com.weflo.backend.domain.component.service;

import com.weflo.backend.domain.component.domain.Component;
import com.weflo.backend.domain.component.domain.ComponentType;
import com.weflo.backend.domain.component.dto.ComponentResponse;
import com.weflo.backend.domain.component.dto.DroneComponentResponse;
import com.weflo.backend.domain.component.repository.ComponentRepository;
import com.weflo.backend.domain.component.repository.DroneComponentRepository;
import com.weflo.backend.domain.drone.domain.DroneComponent;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComponentService {
    private final ComponentRepository componentRepository;
    private final DroneComponentRepository droneComponentRepository;

    public List<DroneComponentResponse> getDroneComponentsByPoint(Long droneId, Long point) {
        List<DroneComponent> findDroneComponents = droneComponentRepository.findByDroneIdAndPointGreaterThanEqual(
                droneId, point);

        return DroneComponentResponse.ofList(findDroneComponents);
    }

    public List<ComponentResponse> getComponentsByTypes(String[] types) {
        List<ComponentResponse> droneComponentResult = new ArrayList<>();

        for (String type : types) {
            ComponentType findType = ComponentType.findTypeByName(type);
            List<Component> findComponentsByType = componentRepository.findByType(findType);
            droneComponentResult.addAll(ComponentResponse.ofList(findComponentsByType));
        }

        return droneComponentResult;
    }
}
