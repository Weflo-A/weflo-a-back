package com.weflo.backend.domain.testresult.service;

import com.weflo.backend.domain.component.domain.Component;
import com.weflo.backend.domain.component.domain.ComponentType;
import com.weflo.backend.domain.component.domain.Part;
import com.weflo.backend.domain.component.dto.ComponentResponse;
import com.weflo.backend.domain.component.dto.DroneComponentResponse;
import com.weflo.backend.domain.testresult.domain.TestResult;
import com.weflo.backend.domain.testresult.repository.TestResultRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestResultService {

    private final TestResultRepository testResultRepository;

    @Transactional(readOnly = true)
    public List<DroneComponentResponse> getTestResultComponents(Long droneId, LocalDateTime start, LocalDateTime end) {
        TestResult findTestResult = testResultRepository.findByDroneIdAndCreateDateBetween(droneId, start, end);
        List<Component> components = findTestResult.getComponents();

        List<DroneComponentResponse> responses = new ArrayList<>();

        for (Component component : components) {
            DroneComponentResponse response = DroneComponentResponse.builder()
                    .image(component.getImage())
                    .description(component.getDescription())
                    .name(component.getName())
                    .price(component.getPrice())
                    .part(component.getPart())
                    .type(component.getType())
                    .star(component.getStar())
                    .build();

            checkPartAndType(response, findTestResult, component.getPart(), component.getType());
            responses.add(response);
        }

        return responses;
    }

    private void checkPartAndType(DroneComponentResponse response, TestResult result, Part part, ComponentType type) {
        if (Part.PART1.equals(part) && ComponentType.BLADE.equals(type)) {
            response.setPoint((long)result.getPart1Blade());
        }

        if (Part.PART1.equals(part) && ComponentType.ESC.equals(type)) {
            response.setPoint((long)result.getPart1Esc());
        }

        if (Part.PART1.equals(part) && ComponentType.MOTOR.equals(type)) {
            response.setPoint((long)result.getPart1Motor());
        }

        if (Part.PART2.equals(part) && ComponentType.BLADE.equals(type)) {
            response.setPoint((long)result.getPart2Blade());
        }

        if (Part.PART2.equals(part) && ComponentType.ESC.equals(type)) {
            response.setPoint((long)result.getPart2Esc());
        }

        if (Part.PART2.equals(part) && ComponentType.MOTOR.equals(type)) {
            response.setPoint((long)result.getPart2Motor());
        }

        if (Part.PART3.equals(part) && ComponentType.BLADE.equals(type)) {
            response.setPoint((long)result.getPart3Blade());
        }

        if (Part.PART3.equals(part) && ComponentType.ESC.equals(type)) {
            response.setPoint((long)result.getPart3Esc());
        }

        if (Part.PART3.equals(part) && ComponentType.MOTOR.equals(type)) {
            response.setPoint((long)result.getPart3Motor());
        }

        if (Part.PART4.equals(part) && ComponentType.BLADE.equals(type)) {
            response.setPoint((long)result.getPart4Blade());
        }

        if (Part.PART4.equals(part) && ComponentType.ESC.equals(type)) {
            response.setPoint((long)result.getPart4Esc());
        }

        if (Part.PART4.equals(part) && ComponentType.MOTOR.equals(type)) {
            response.setPoint((long)result.getPart4Motor());
        }
    }
}
