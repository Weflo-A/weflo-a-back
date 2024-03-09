package com.weflo.backend.domain.drone.service;

import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.domain.DroneGroup;
import com.weflo.backend.domain.drone.dto.response.*;
import com.weflo.backend.domain.drone.repository.DroneGroupInfoRepository;
import com.weflo.backend.domain.drone.repository.DroneRepository;
import com.weflo.backend.domain.testresult.domain.TestResult;
import com.weflo.backend.domain.testresult.repository.TestResultRepository;
import com.weflo.backend.global.common.service.FindService;
import com.weflo.backend.global.error.ErrorCode;
import com.weflo.backend.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashBoardService {
    private final DroneRepository droneRepository;
    private final TestResultRepository testResultRepository;
    private final DroneGroupInfoRepository droneGroupInfoRepository;
    private final FindService findService;
    public DroneDetailResponse getDroneDetail(Long droneId){
        Drone drone = findService.findDroneById(droneId);
        List<TestResult> testResults = findTestResultById(droneId);
        TestResult testResult = testResultRepository.findFirstByDroneIdOrderByCreateDateDesc(droneId);
        DroneInfoResponse droneInfoResponse = createDroneInfoResponse(drone,testResult);
        List<TimeLineResponse> timeLineResponses = createTimeLineResponse(testResults);
        List<TestListResponse> testListResponses = createTestListResponse(testResults);
        DroneGroup droneGroup = droneGroupInfoRepository.findTopByDroneIdOrderByCreateDateDesc(droneId);
        List<DroneListResponse> droneListResponses = createDroneListResponse(droneGroup);
        return DroneDetailResponse.of(droneInfoResponse, timeLineResponses,testListResponses, droneListResponses);
    }
    private DroneGroupListResponse createDroneGroupListResponse(Long droneId){
        DroneGroup droneGroup = droneGroupInfoRepository.findTopByDroneIdOrderByCreateDateDesc(droneId);
        List<DroneListResponse> droneListResponses = createDroneListResponse(droneGroup);
        return DroneGroupListResponse.of(droneGroup.getName(),droneListResponses);
    }
    private List<DroneListResponse> createDroneListResponse(DroneGroup droneGroup){
        List<Drone> drones = droneGroupInfoRepository.findAllDroneByDroneGroupId(droneGroup.getId());
        return drones.stream()
                .map(drone -> DroneListResponse.of(drone))
                .collect(Collectors.toList());
    }
    private DroneInfoResponse createDroneInfoResponse(Drone drone, TestResult testResult){

        return DroneInfoResponse.of(drone,
                findService.getPoint(testResult),
                findService.getMotorPoint(testResult),
                findService.getBladePoint(testResult),
                findService.getEscPoint(testResult));
    }
    private List<TimeLineResponse> createTimeLineResponse(List<TestResult> testResults){
        return testResults.stream()
                .map(testResult ->
                        TimeLineResponse.of(
                                testResult,
                                findService.getEscPoint(testResult),
                                findService.getBladePoint(testResult),
                                findService.getMotorPoint(testResult)))
                .collect(Collectors.toList());
    }
    private List<TestListResponse> createTestListResponse(List<TestResult> testResults){
        return testResults.stream()
                .map(testResult ->
                        TestListResponse.of(
                                testResult,
                                findService.getPoint(testResult)))
                .collect(Collectors.toList());
    }

    private List<TestResult> findTestResultById(Long droneId){
        return testResultRepository.findAllByDroneId(droneId);
    }
}
