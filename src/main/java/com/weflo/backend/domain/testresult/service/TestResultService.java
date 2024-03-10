package com.weflo.backend.domain.testresult.service;

import com.weflo.backend.domain.component.domain.Component;
import com.weflo.backend.domain.component.domain.ComponentType;
import com.weflo.backend.domain.component.domain.Part;
import com.weflo.backend.domain.component.dto.ComponentResponse;
import com.weflo.backend.domain.component.dto.DroneComponentResponse;
import com.weflo.backend.domain.drone.dto.response.dashBoardDetail.TotalScoreResponse;
import com.weflo.backend.domain.testresult.domain.TestResult;
import com.weflo.backend.domain.testresult.dto.ComponentDetailResponse;
import com.weflo.backend.domain.testresult.dto.PartTotalScoreResponse;
import com.weflo.backend.domain.testresult.dto.TestResultDateResponse;
import com.weflo.backend.domain.testresult.dto.TestResultTopSectionResponse;
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
    public List<TestResultDateResponse> getTestResultDates(Long droneId) {
        List<TestResult> findTestResults = testResultRepository.findAllByDroneId(droneId);
        return TestResultDateResponse.ofList(findTestResults);
    }

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
                    .part(component.getPart().getKoreanName())
                    .type(component.getType().getKoreanName())
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

    public TestResultTopSectionResponse generateTopSectionResponse(List<DroneComponentResponse> responses) {
        PartTotalScoreResponse partTotalScoreResponse = generatePartTotalScoreResponse(responses);

        List<ComponentDetailResponse> componentDetailResponses = ComponentDetailResponse.ofList(responses, 2, 5);

        return TestResultTopSectionResponse.builder()
                .totalScore(partTotalScoreResponse)
                .components(componentDetailResponses)
                .build();
    }

    private PartTotalScoreResponse generatePartTotalScoreResponse(List<DroneComponentResponse> responses) {
        List<Long> part1ComponentsPoints = responses.stream()
                .filter(component -> component.getPart().equals(Part.PART1.getKoreanName()))
                .map(DroneComponentResponse::getPoint)
                .toList();

        List<Long> part2ComponentsPoints = responses.stream()
                .filter(component -> component.getPart().equals(Part.PART2.getKoreanName()))
                .map(DroneComponentResponse::getPoint)
                .toList();

        List<Long> part3ComponentsPoints = responses.stream()
                .filter(component -> component.getPart().equals(Part.PART3.getKoreanName()))
                .map(DroneComponentResponse::getPoint)
                .toList();

        List<Long> part4ComponentsPoints = responses.stream()
                .filter(component -> component.getPart().equals(Part.PART4.getKoreanName()))
                .map(DroneComponentResponse::getPoint)
                .toList();

        for (Long part1ComponentsPoint : part1ComponentsPoints) {
            System.out.println("part1ComponentsPoint = " + part1ComponentsPoint);
        }
        
        int part1Sum = part1ComponentsPoints.stream().mapToInt(Long::intValue).sum()/3;
        int part2Sum = part2ComponentsPoints.stream().mapToInt(Long::intValue).sum()/3;
        int part3Sum = part3ComponentsPoints.stream().mapToInt(Long::intValue).sum()/3;
        int part4Sum = part4ComponentsPoints.stream().mapToInt(Long::intValue).sum()/3;

        return PartTotalScoreResponse.builder()
                .part1(part1Sum)
                .part2(part2Sum)
                .part3(part3Sum)
                .part4(part4Sum)
                .build();
    }
}
