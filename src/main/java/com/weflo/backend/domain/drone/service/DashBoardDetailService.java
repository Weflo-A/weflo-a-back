package com.weflo.backend.domain.drone.service;

import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.dto.response.dashBoardDetail.*;
import com.weflo.backend.domain.testresult.domain.TestResult;
import com.weflo.backend.domain.testresult.repository.TestResultRepository;
import com.weflo.backend.global.common.service.FindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DashBoardDetailService {
    private final TestResultRepository testResultRepository;
    private final FindService findService;
    public DashBoardDetailResponse getDashBoardDetail(Long droneId, String date){
        Drone drone = findService.findDroneById(droneId);
        TestResult testResult = findTestResultByDroneIdAndDate(droneId, date);
        DroneTestInfoResponse droneTestInfoResponse = createDroneTestInfoResponse(drone, testResult);
        DroneTestResultResponse droneTestResultResponse = createDroneTestResultResponse(testResult);
        List<DroneScoreResponse> droneScoreResponses = createDroneScoreResponses(testResult);
        DroneScoreAvgResponse droneScoreAvgResponse = createDroneScoreAvgResponse(testResult);
    }
    private DroneScoreAvgResponse createDroneScoreAvgResponse(TestResult testResult){
        return DroneScoreAvgResponse.of(
                findService.getMotorPoint(testResult),
                findService.getBladePoint(testResult),
                findService.getEscPoint(testResult),
                findService.getPoint(testResult));
    }
    private List<DroneScoreResponse> createDroneScoreResponses(TestResult testResult) {
        List<DroneScoreResponse> droneScoreResponses = new ArrayList<>();
        DroneScoreResponse part1Score = DroneScoreResponse.createDroneScoreResponse(testResult.getPart1Motor(), testResult.getPart1Blade(), testResult.getPart1Esc(), findService.getPart1Point(testResult));
        droneScoreResponses.add(part1Score);
        DroneScoreResponse part2Score = DroneScoreResponse.createDroneScoreResponse(testResult.getPart2Motor(), testResult.getPart2Blade(), testResult.getPart2Esc(), findService.getPart2Point(testResult));
        droneScoreResponses.add(part2Score);
        DroneScoreResponse part3Score = DroneScoreResponse.createDroneScoreResponse(testResult.getPart3Motor(), testResult.getPart3Blade(), testResult.getPart3Esc(), findService.getPart3Point(testResult));
        droneScoreResponses.add(part3Score);
        DroneScoreResponse part4Score = DroneScoreResponse.createDroneScoreResponse(testResult.getPart4Motor(), testResult.getPart4Blade(), testResult.getPart4Esc(), findService.getPart4Point(testResult));
        droneScoreResponses.add(part4Score);
        return droneScoreResponses;
    }

    private DroneTestResultResponse createDroneTestResultResponse(TestResult testResult){
        return DroneTestResultResponse.of(
                findService.getPoint(testResult),
                findService.getPart1Point(testResult),
                findService.getPart2Point(testResult),
                findService.getPart3Point(testResult),
                findService.getPart4Point(testResult));
    }
    private TestResult findTestResultByDroneIdAndDate(Long droneId, String date){
        return testResultRepository.findByDroneIdAndCreateDate(droneId, LocalDateTime.parse(date));
    }
    private DroneTestInfoResponse createDroneTestInfoResponse(Drone drone, TestResult testResult){
        return DroneTestInfoResponse.of(drone,testResult);
    }
}
