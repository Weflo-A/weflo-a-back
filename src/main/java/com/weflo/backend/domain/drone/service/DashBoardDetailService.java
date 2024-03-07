package com.weflo.backend.domain.drone.service;

import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.dto.request.DashBoardDetailRequest;
import com.weflo.backend.domain.drone.dto.response.dashBoardDetail.*;
import com.weflo.backend.domain.testresult.domain.TestResult;
import com.weflo.backend.domain.testresult.repository.TestResultRepository;
import com.weflo.backend.global.common.service.FindService;
import com.weflo.backend.global.error.ErrorCode;
import com.weflo.backend.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DashBoardDetailService {
    private final TestResultRepository testResultRepository;
    private final FindService findService;
    public DashBoardDetailResponse getDashBoardDetail(DashBoardDetailRequest dashBoardDetailRequest){
        Drone drone = findService.findDroneById(dashBoardDetailRequest.getDroneId());
        TestResult testResult = findTestResultByDroneIdAndDate(dashBoardDetailRequest.getDroneId(), dashBoardDetailRequest.getDate());
        DroneTestInfoResponse droneTestInfoResponse = createDroneTestInfoResponse(drone);
        DroneTestResultResponse droneTestResultResponse = createDroneTestResultResponse(testResult);
        List<DroneScoreResponse> droneScoreResponses = createDroneScoreResponses(testResult);
        DroneScoreAvgResponse droneScoreAvgResponse = createDroneScoreAvgResponse(testResult);
        DroneTotalScoreResponse droneTotalScoreResponse =createDroneTotalScoreResponse(testResult);
        List<DroneWarningResponse> droneWarningResponses =createDroneWarningResponses(testResult);
        String warningPart = findWarningPart(testResult);
        return DashBoardDetailResponse.of(
                droneTestInfoResponse,
                droneTestResultResponse,
                droneScoreResponses,
                droneScoreAvgResponse,
                droneTotalScoreResponse,
                droneWarningResponses,
                warningPart);
    }
    private String findWarningPart(TestResult testResult){
        int part1 = findService.getPart1Point(testResult);
        int part2 = findService.getPart2Point(testResult);
        int part3 = findService.getPart3Point(testResult);
        int part4 = findService.getPart4Point(testResult);
        int value = Math.min(part1, Math.min(part2, Math.min(part3, part4)));
        return computeWaringPart(value,part1,part2,part3);
    }
    private String computeWaringPart(int value,int part1, int part2, int part3){
        if (value == part1) {
            return "PART1";
        } else if (value == part2) {
            return "PART2";
        } else if (value == part3) {
            return "PART3";
        } else {
            return "PART4";
        }
    }
    private List<DroneWarningResponse> createDroneWarningResponses(TestResult testResult){
        List<DroneWarningResponse> droneWarningResponses = new ArrayList<>();
        DroneWarningResponse droneWarningResponse1 = DroneWarningResponse.createDroneWarningResponse("PART1", "Motor", testResult.getPart1Motor());
        droneWarningResponses.add(droneWarningResponse1);
        DroneWarningResponse droneWarningResponse2 = DroneWarningResponse.createDroneWarningResponse("PART1", "Blade", testResult.getPart1Blade());
        droneWarningResponses.add(droneWarningResponse2);
        DroneWarningResponse droneWarningResponse3 = DroneWarningResponse.createDroneWarningResponse("PART1", "Esc", testResult.getPart1Esc());
        droneWarningResponses.add(droneWarningResponse3);

        DroneWarningResponse droneWarningResponse4 = DroneWarningResponse.createDroneWarningResponse("PART2", "Motor", testResult.getPart2Motor());
        droneWarningResponses.add(droneWarningResponse4);
        DroneWarningResponse droneWarningResponse5 = DroneWarningResponse.createDroneWarningResponse("PART2", "Blade", testResult.getPart2Blade());
        droneWarningResponses.add(droneWarningResponse5);
        DroneWarningResponse droneWarningResponse6 = DroneWarningResponse.createDroneWarningResponse("PART2", "Esc", testResult.getPart2Esc());
        droneWarningResponses.add(droneWarningResponse6);

        DroneWarningResponse droneWarningResponse7 = DroneWarningResponse.createDroneWarningResponse("PART3", "Motor", testResult.getPart3Motor());
        droneWarningResponses.add(droneWarningResponse7);
        DroneWarningResponse droneWarningResponse8 = DroneWarningResponse.createDroneWarningResponse("PART3", "Blade", testResult.getPart3Blade());
        droneWarningResponses.add(droneWarningResponse8);
        DroneWarningResponse droneWarningResponse9 = DroneWarningResponse.createDroneWarningResponse("PART3", "Esc", testResult.getPart3Esc());
        droneWarningResponses.add(droneWarningResponse9);

        DroneWarningResponse droneWarningResponse10 = DroneWarningResponse.createDroneWarningResponse("PART4", "Motor", testResult.getPart4Motor());
        droneWarningResponses.add(droneWarningResponse10);
        DroneWarningResponse droneWarningResponse11 = DroneWarningResponse.createDroneWarningResponse("PART4", "Blade", testResult.getPart4Blade());
        droneWarningResponses.add(droneWarningResponse11);
        DroneWarningResponse droneWarningResponse12 = DroneWarningResponse.createDroneWarningResponse("PART4", "Esc", testResult.getPart4Esc());
        droneWarningResponses.add(droneWarningResponse12);

        List<DroneWarningResponse> top5Warnings = droneWarningResponses.stream()
                .sorted(Comparator.comparingInt(DroneWarningResponse::getScore))
                .limit(5)
                .collect(Collectors.toList());


        return top5Warnings;
    }
    private DroneTotalScoreResponse createDroneTotalScoreResponse(TestResult testResult){
        TestResult closetTestResult = testResultRepository.findClosestPreviousByCreateDate(testResult.getCreateDate());
        return DroneTotalScoreResponse.of(testResult,closetTestResult);
    }
    private TotalScoreResponse createTotalScoreResponse(TestResult testResult){
        return TotalScoreResponse.of(
                findService.getPart1Point(testResult),
                findService.getPart2Point(testResult),
                findService.getPart3Point(testResult),
                findService.getPart4Point(testResult)
        );
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
        DroneScoreResponse part1Score = DroneScoreResponse.createDroneScoreResponse("PART1",testResult.getPart1Motor(), testResult.getPart1Blade(), testResult.getPart1Esc(), findService.getPart1Point(testResult));
        droneScoreResponses.add(part1Score);
        DroneScoreResponse part2Score = DroneScoreResponse.createDroneScoreResponse("PART2",testResult.getPart2Motor(), testResult.getPart2Blade(), testResult.getPart2Esc(), findService.getPart2Point(testResult));
        droneScoreResponses.add(part2Score);
        DroneScoreResponse part3Score = DroneScoreResponse.createDroneScoreResponse("PART3",testResult.getPart3Motor(), testResult.getPart3Blade(), testResult.getPart3Esc(), findService.getPart3Point(testResult));
        droneScoreResponses.add(part3Score);
        DroneScoreResponse part4Score = DroneScoreResponse.createDroneScoreResponse("PART4",testResult.getPart4Motor(), testResult.getPart4Blade(), testResult.getPart4Esc(), findService.getPart4Point(testResult));
        droneScoreResponses.add(part4Score);
        return droneScoreResponses;
    }

    private DroneTestResultResponse createDroneTestResultResponse(TestResult testResult){
        return DroneTestResultResponse.of(createTotalScoreResponse(testResult),
                findService.getPoint(testResult),
                createDroneScoreResponses(testResult));
    }
    private TestResult findTestResultByDroneIdAndDate(Long droneId, String date){
        LocalDateTime localDateTime = LocalDateTime.parse(date + "T00:00:00");

        // 변환된 LocalDate를 사용하여 Repository 메서드 호출
        return testResultRepository.findByDroneIdAndCreateDateYearAndCreateDateMonthAndCreateDateDay(droneId, localDateTime.getYear(), localDateTime.getMonthValue(),localDateTime.getDayOfMonth()).orElseThrow(()->new EntityNotFoundException(ErrorCode.TEST_RESULT_NOT_FOUND));
    }
    private DroneTestInfoResponse createDroneTestInfoResponse(Drone drone){
        List<TestResult> testResults = testResultRepository.findAllByDroneId(drone.getId());
        List<String> testDates= testResults.stream().map(testResult->testResult.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"))).collect(Collectors.toList());
        return DroneTestInfoResponse.of(drone,testDates);
    }
}
