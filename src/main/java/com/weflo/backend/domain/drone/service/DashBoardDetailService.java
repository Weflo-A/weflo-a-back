package com.weflo.backend.domain.drone.service;

import com.weflo.backend.domain.drone.dto.response.dashBoardDetail.DashBoardDetailResponse;
import com.weflo.backend.domain.drone.dto.response.dashBoardDetail.DroneTestInfoResponse;
import com.weflo.backend.domain.testresult.domain.TestResult;
import com.weflo.backend.domain.testresult.repository.TestResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class DashBoardDetailService {
    private final TestResultRepository testResultRepository;
    public DashBoardDetailResponse getDashBoardDetail(Long droneId, String date){
        Drone
        TestResult testResult = findTestResultByDroneIdAndDate(droneId, date);
        DroneTestInfoResponse droneTestInfoResponse = createDroneTestInfoResponse(testResult);
    }
    private TestResult findTestResultByDroneIdAndDate(Long droneId, String date){
        return testResultRepository.findByDroneIdAndCreateDate(droneId, LocalDateTime.parse(date));
    }
    private DroneTestInfoResponse createDroneTestInfoResponse(TestResult testResult){

    }
}
