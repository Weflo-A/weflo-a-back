package com.weflo.backend.domain.drone.service;

import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.dto.response.*;
import com.weflo.backend.domain.drone.repository.DroneRepository;
import com.weflo.backend.domain.testresult.domain.TestResult;
import com.weflo.backend.domain.testresult.repository.TestResultRepository;
import com.weflo.backend.global.error.ErrorCode;
import com.weflo.backend.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashBoardService {
    private final DroneRepository droneRepository;
    private final TestResultRepository testResultRepository;
    public DroneDetailResponse getDroneDetail(Long droneId){
        Drone drone = findDroneById(droneId);
        List<TestResult> testResults = findTestResultById(droneId);
        DroneInfoResponse droneInfoResponse = createDroneInfoResponse(drone);
        List<TimeLineResponse> timeLineResponses = createTimeLineResponse(testResults);
        List<TestListResponse> testListResponses = createTestListResponse(testResults);
        List<DroneListResponse> droneListResponses = createGroupListResponse();
        return DroneDetailResponse.of(droneInfoResponse, timeLineResponses, testListResponses, droneListResponses, drone.getCost());
    }
    private List<DroneListResponse> createGroupListResponse(){
        List<Drone> drones = getAllDrone();
        return drones.stream()
                .map(drone -> DroneListResponse.of(drone))
                .collect(Collectors.toList());
    }
    private List<Drone> getAllDrone(){
        return droneRepository.findAll();
    }
    private Drone findDroneById(Long droneId){
        return droneRepository.findById(droneId).orElseThrow(() -> new EntityNotFoundException(ErrorCode.DRONE_NOT_FOUND));
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
