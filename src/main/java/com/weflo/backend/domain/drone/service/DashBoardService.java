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

@RequiredArgsConstructor
@Service
public class DashBoardService {
    private final DroneRepository droneRepository;
    private final TestResultRepository testResultRepository;
    private final DroneGroupInfoRepository droneGroupInfoRepository;
    private final FindService findService;
    public DroneDetailResponse getDroneDetail(Long droneId){
        Drone drone = findService.findDroneById(droneId);
        List<TestResult> testResults = findTestResultById(droneId);
        DroneInfoResponse droneInfoResponse = createDroneInfoResponse(drone);
        List<TimeLineResponse> timeLineResponses = createTimeLineResponse(testResults);
        List<TestListResponse> testListResponses = createTestListResponse(testResults);
        DroneGroupListResponse droneGroupListResponse = createDroneGroupListResponse(droneId);
        return DroneDetailResponse.of(droneInfoResponse, timeLineResponses, testListResponses, droneGroupListResponse, drone.getCost());
    }
    private DroneGroupListResponse createDroneGroupListResponse(Long droneId){
        DroneGroup droneGroup = droneGroupInfoRepository.findTopByDroneIdOrderByCreatedAtDesc(droneId);
        List<DroneListResponse> droneListResponses = createDroneListResponse(droneGroup);
        return DroneGroupListResponse.of(droneGroup.getName(),droneListResponses);
    }
    private List<DroneListResponse> createDroneListResponse(DroneGroup droneGroup){
        List<Drone> drones = droneGroupInfoRepository.findAllDroneByDroneGroupId(droneGroup.getId());
        return drones.stream()
                .map(drone -> DroneListResponse.of(drone.getName()))
                .collect(Collectors.toList());
    }
    private DroneInfoResponse createDroneInfoResponse(Drone drone){
        return DroneInfoResponse.of(drone);
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
