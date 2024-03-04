package com.weflo.backend.domain.component.service;

import com.weflo.backend.domain.component.domain.Component;
import com.weflo.backend.domain.component.domain.ComponentType;
import com.weflo.backend.domain.component.domain.Part;
import com.weflo.backend.domain.component.dto.ComponentResponse;
import com.weflo.backend.domain.component.repository.ComponentRepository;
import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.domain.DroneComponent;
import com.weflo.backend.domain.drone.repository.DroneRepository;
import com.weflo.backend.global.error.ErrorCode;
import com.weflo.backend.global.error.exception.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ComponentService {
    private final ComponentRepository componentRepository;
    private final DroneRepository droneRepository;

    @Transactional(readOnly = true)
    public List<ComponentResponse> getDroneComponents(Long droneId) {
        Drone findDrone = droneRepository.findById(droneId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.DRONE_NOT_FOUND));

        List<DroneComponent> droneComponents = findDrone.getDroneComponents();
        List<Component> components = droneComponents.stream().map(DroneComponent::getComponent).toList();

        return ComponentResponse.ofList(components);
    }

    @Transactional
    public void setData() {
        Component componentA = Component.builder()
                .name("부품 A")
                .part(Part.PART1)
                .price(1000)
                .description("부품 A 정의")
                .type(ComponentType.ESC)
                .image("부품 A 이미지 경로")
                .build();

        Component componentB = Component.builder()
                .name("부품 A")
                .part(Part.PART2)
                .price(2000)
                .description("부품 B 정의")
                .type(ComponentType.BLADE)
                .image("부품 B 이미지 경로")
                .build();

        Component componentC = Component.builder()
                .name("부품 C")
                .part(Part.PART3)
                .price(3000)
                .description("부품 C 정의")
                .type(ComponentType.MOTOR)
                .image("부품 C 이미지 경로")
                .build();

        Component componentD = Component.builder()
                .name("부품 D")
                .part(Part.PART1)
                .price(4000)
                .description("부품 D 정의")
                .type(ComponentType.BLADE)
                .image("부품 D 이미지 경로")
                .build();

        componentRepository.save(componentA);
        componentRepository.save(componentB);
        componentRepository.save(componentC);
        componentRepository.save(componentD);
    }
}
