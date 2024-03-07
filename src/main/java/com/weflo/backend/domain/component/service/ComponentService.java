package com.weflo.backend.domain.component.service;

import com.weflo.backend.domain.component.domain.Component;
import com.weflo.backend.domain.component.domain.ComponentType;
import com.weflo.backend.domain.component.dto.ComponentResponse;
import com.weflo.backend.domain.component.dto.ComponentsByModelsResponse;
import com.weflo.backend.domain.component.dto.DroneComponentResponse;
import com.weflo.backend.domain.component.repository.ComponentRepository;
import com.weflo.backend.domain.component.repository.DroneComponentRepository;
import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.domain.DroneComponent;
import com.weflo.backend.domain.drone.domain.DroneModel;
import com.weflo.backend.domain.drone.repository.DroneRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComponentService {
    private final ComponentRepository componentRepository;
    private final DroneComponentRepository droneComponentRepository;
    private final DroneRepository droneRepository;

    public List<DroneComponentResponse> getDroneComponentsByPointUp(Long droneId, Long point) {
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

    public List<DroneComponentResponse> getDroneComponentsByPointDown(Long droneId, Long point) {
        List<DroneComponent> findDroneComponents = droneComponentRepository.findByDroneIdAndPointLessThanEqual(
                droneId, point);

        return DroneComponentResponse.ofList(findDroneComponents);
    }

    public List<ComponentsByModelsResponse> getDroneComponentsByModels(Long point) {
        List<Drone> allDrones = droneRepository.findAll();
        List<ComponentsByModelsResponse> result = new ArrayList<>();

        while (!allDrones.isEmpty()) {
            Drone firstDrone = allDrones.get(0);
            List<Drone> filteredDrones = allDrones.stream().filter(drone -> drone.getModel().equals(firstDrone.getModel()))
                    .toList();

            Map<ComponentType, Long> componentStatus = new HashMap<>();

            for (Drone drone : filteredDrones) {
                List<DroneComponentResponse> findComponents = getDroneComponentsByPointDown(drone.getId(),
                        point);

                for (DroneComponentResponse findComponent : findComponents) {
                    if (!componentStatus.containsKey(findComponent.getType())) {
                        componentStatus.put(findComponent.getType(), 1L);
                    } else {
                        componentStatus.put(findComponent.getType(), componentStatus.get(findComponent.getType()) + 1L);
                    }
                }
            }

            ComponentsByModelsResponse response = ComponentsByModelsResponse.of(firstDrone.getModel(),
                    componentStatus);

            result.add(response);

            allDrones.stream()
                    .filter(drone -> drone.getModel().equals(firstDrone.getModel()))
                    .toList()
                    .forEach(allDrones::remove);
        }

        return result;
    }
}
