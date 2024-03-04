package com.weflo.backend.domain.drone.service;

import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.dto.response.dashBoardDetail.DashBoardDetailResponse;
import com.weflo.backend.domain.drone.dto.response.dashBoardDetail.DroneTestInfoResponse;
import com.weflo.backend.domain.testresult.domain.TestResult;
import com.weflo.backend.domain.testresult.repository.TestResultRepository;
import com.weflo.backend.global.common.service.FindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class DashBoardDetailService {
    private final TestResultRepository testResultRepository;
    private final FindService findService;
    public DashBoardDetailResponse getDashBoardDetail(Long droneId, String date){
        Drone drone = findService.findDroneById(droneId);
        TestResult testResult = findTestResultByDroneIdAndDate(droneId, date);
        DroneTestInfoResponse droneTestInfoResponse = createDroneTestInfoResponse(drone, testResult);
    }
    private TestResult findTestResultByDroneIdAndDate(Long droneId, String date){
        return testResultRepository.findByDroneIdAndCreateDate(droneId, LocalDateTime.parse(date));
    }
    private DroneTestInfoResponse createDroneTestInfoResponse(Drone drone, TestResult testResult){
        return DroneTestInfoResponse.of(drone,testResult);
    }
}
