package com.weflo.backend.domain.drone.service;

import com.weflo.backend.domain.component.domain.DroneComponent;
import com.weflo.backend.domain.component.repository.DroneComponentRepository;
import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.dto.request.DroneDetailRequest;
import com.weflo.backend.domain.drone.dto.response.DroneDetailResponse;
import com.weflo.backend.domain.drone.dto.response.DroneInfoResponse;
import com.weflo.backend.domain.drone.dto.response.TestListResponse;
import com.weflo.backend.domain.drone.dto.response.TimeLineResponse;
import com.weflo.backend.domain.drone.repository.DroneRepository;
import com.weflo.backend.domain.testresult.domain.TestResult;
import com.weflo.backend.domain.testresult.repository.TestResultRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DashBoardService {
    private final DroneRepository droneRepository;
    private final DroneComponentRepository droneComponentRepository;
    private final TestResultRepository testResultRepository;
    public DroneDetailResponse getDroneDetail(DroneDetailRequest droneDetailRequest){
        Drone drone = findDroneById(droneDetailRequest.getDroneId());
        List<TestResult> testResults = findTestResultById(droneDetailRequest.getDroneId());
        DroneInfoResponse droneInfoResponse = createDroneInfoResponse(drone);
        List<TimeLineResponse> timeLineResponses = createTimeLineResponse(testResults);
        List<TestListResponse> testListResponses = createTestListResponse(testResults);
    }
    private Drone findDroneById(Long droneId){
        return droneRepository.findById(droneId).orElseThrow();
    }
    private DroneInfoResponse createDroneInfoResponse(Drone drone){
        return DroneInfoResponse.of(drone);
    }
    private List<TimeLineResponse> createTimeLineResponse(List<TestResult> testResults){
        return testResults.stream()
                .map(testResult ->
                        TimeLineResponse.of(
                                testResult,
                                getEscPoint(testResult),
                                getBladePoint(testResult),
                                getMotorPoint(testResult)))
                .collect(Collectors.toList());
    }
    private List<TestListResponse> createTestListResponse(List<TestResult> testResults){
        return testResults.stream()
                .map(testResult ->
                        TestListResponse.of(
                                testResult,
                                getPoint(testResult)))
                .collect(Collectors.toList());
    }
    private int getPoint(TestResult testResult){
        return (getEscPoint(testResult)+getBladePoint(testResult)+getMotorPoint(testResult))/3;
    }
    private int getEscPoint(TestResult testResult){
        return (testResult.getPart1Esc()+testResult.getPart2Esc()+testResult.getPart3Esc()+testResult.getPart4Esc())/4;

    }
    private int getBladePoint(TestResult testResult){
        return (testResult.getPart1Blade()+testResult.getPart2Blade()+testResult.getPart3Blade()+testResult.getPart4Blade())/4;

    }
    private int getMotorPoint(TestResult testResult){
        return (testResult.getPart1Motor()+testResult.getPart2Motor()+testResult.getPart3Motor()+testResult.getPart4Motor())/4;

    }
    private List<TestResult> findTestResultById(Long droneId){
        return testResultRepository.findAllByDroneId(droneId);
    }
}
