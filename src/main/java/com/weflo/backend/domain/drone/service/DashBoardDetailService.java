package com.weflo.backend.domain.drone.service;

import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.dto.request.DashBoardDetailRequest;
import com.weflo.backend.domain.drone.dto.request.SortScoreListRequest;
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
        TestResult testResult = findTestResultByDroneIdAndDate(dashBoardDetailRequest.getDroneId(), dashBoardDetailRequest.getYear(), dashBoardDetailRequest.getMonth(),dashBoardDetailRequest.getDay());
        DroneTestInfoResponse droneTestInfoResponse = createDroneTestInfoResponse(drone);
        DroneTestResultResponse droneTestResultResponse = createDroneTestResultResponse(testResult);
        List<DroneScoreResponse> droneScoreResponses = createDroneScoreResponses(testResult);
        List<DroneScoreAvgResponse> droneScoreAvgResponses = createDroneScoreAvgResponse(testResult);
        DroneTotalScoreResponse droneTotalScoreResponse =createDroneTotalScoreResponse(testResult);
        List<DroneWarningResponse> droneWarningResponses =createDroneWarningResponses(testResult);
        String warningPart = findWarningPart(testResult);
        return DashBoardDetailResponse.of(
                droneTestInfoResponse,
                droneTestResultResponse,
                droneScoreResponses,
                droneScoreAvgResponses,
                droneTotalScoreResponse,
                droneWarningResponses,
                warningPart);
    }
    public List<DroneScoreResponse> sortDroneScoreResponseList(SortScoreListRequest sortScoreListRequest){
        TestResult testResult = findTestResultByDroneIdAndDate(sortScoreListRequest.getDroneId(), sortScoreListRequest.getYear(), sortScoreListRequest.getMonth(), sortScoreListRequest.getDay());
        List<DroneScoreResponse> droneScoreResponses = createDroneScoreResponses(testResult);
        return sort(droneScoreResponses, sortScoreListRequest.getFilter());
    }
    private List<DroneScoreResponse> sort(List<DroneScoreResponse> droneScoreResponses, String filter){
        if ("motor".equals(filter)) {
            droneScoreResponses.sort(Comparator.comparingInt(DroneScoreResponse::getMotor).reversed());
        } else if ("blade".equals(filter)) {
            droneScoreResponses.sort(Comparator.comparing(DroneScoreResponse::getBlade).reversed());
        } else if ("esc".equals(filter)) {
            droneScoreResponses.sort(Comparator.comparingInt(DroneScoreResponse::getEsc).reversed());
        } else if ("total".equals(filter)) {
            droneScoreResponses.sort(Comparator.comparingInt(DroneScoreResponse::getTotal).reversed());
        }
        return droneScoreResponses;
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
            return "구동부 01";
        } else if (value == part2) {
            return "구동부 02";
        } else if (value == part3) {
            return "구동부 03";
        } else {
            return "구동부 04";
        }
    }
    private List<DroneWarningResponse> createDroneWarningResponses(TestResult testResult){
        List<DroneWarningResponse> droneWarningResponses = new ArrayList<>();
        DroneWarningResponse droneWarningResponse1 = DroneWarningResponse.createDroneWarningResponse("구동부 01", "Motor", testResult.getPart1Motor());
        droneWarningResponses.add(droneWarningResponse1);
        DroneWarningResponse droneWarningResponse2 = DroneWarningResponse.createDroneWarningResponse("구동부 01", "Blade", testResult.getPart1Blade());
        droneWarningResponses.add(droneWarningResponse2);
        DroneWarningResponse droneWarningResponse3 = DroneWarningResponse.createDroneWarningResponse("구동부 01", "Esc", testResult.getPart1Esc());
        droneWarningResponses.add(droneWarningResponse3);

        DroneWarningResponse droneWarningResponse4 = DroneWarningResponse.createDroneWarningResponse("구동부 02", "Motor", testResult.getPart2Motor());
        droneWarningResponses.add(droneWarningResponse4);
        DroneWarningResponse droneWarningResponse5 = DroneWarningResponse.createDroneWarningResponse("구동부 02", "Blade", testResult.getPart2Blade());
        droneWarningResponses.add(droneWarningResponse5);
        DroneWarningResponse droneWarningResponse6 = DroneWarningResponse.createDroneWarningResponse("구동부 02", "Esc", testResult.getPart2Esc());
        droneWarningResponses.add(droneWarningResponse6);

        DroneWarningResponse droneWarningResponse7 = DroneWarningResponse.createDroneWarningResponse("구동부 03", "Motor", testResult.getPart3Motor());
        droneWarningResponses.add(droneWarningResponse7);
        DroneWarningResponse droneWarningResponse8 = DroneWarningResponse.createDroneWarningResponse("구동부 03", "Blade", testResult.getPart3Blade());
        droneWarningResponses.add(droneWarningResponse8);
        DroneWarningResponse droneWarningResponse9 = DroneWarningResponse.createDroneWarningResponse("구동부 03", "Esc", testResult.getPart3Esc());
        droneWarningResponses.add(droneWarningResponse9);

        DroneWarningResponse droneWarningResponse10 = DroneWarningResponse.createDroneWarningResponse("구동부 04", "Motor", testResult.getPart4Motor());
        droneWarningResponses.add(droneWarningResponse10);
        DroneWarningResponse droneWarningResponse11 = DroneWarningResponse.createDroneWarningResponse("구동부 04", "Blade", testResult.getPart4Blade());
        droneWarningResponses.add(droneWarningResponse11);
        DroneWarningResponse droneWarningResponse12 = DroneWarningResponse.createDroneWarningResponse("구동부 04", "Esc", testResult.getPart4Esc());
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
    private List<DroneScoreAvgResponse> createDroneScoreAvgResponse(TestResult testResult){
        List<DroneScoreAvgResponse> droneScoreAvgResponses = new ArrayList<>();
        droneScoreAvgResponses.add(DroneScoreAvgResponse.createDroneScoreAvg("모터", findService.getMotorPoint(testResult)));
        droneScoreAvgResponses.add(DroneScoreAvgResponse.createDroneScoreAvg("블레이드", findService.getBladePoint(testResult)));
        droneScoreAvgResponses.add(DroneScoreAvgResponse.createDroneScoreAvg("ESC", findService.getEscPoint(testResult)));
        droneScoreAvgResponses.add(DroneScoreAvgResponse.createDroneScoreAvg("종합", findService.getPoint(testResult)));
        return droneScoreAvgResponses;
    }
    private List<DroneScoreResponse> createDroneScoreResponses(TestResult testResult) {
        List<DroneScoreResponse> droneScoreResponses = new ArrayList<>();
        DroneScoreResponse part1Score = DroneScoreResponse.createDroneScoreResponse("01","구동부 01",testResult.getPart1Motor(), testResult.getPart1Blade(), testResult.getPart1Esc(), findService.getPart1Point(testResult));
        droneScoreResponses.add(part1Score);
        DroneScoreResponse part2Score = DroneScoreResponse.createDroneScoreResponse("02","구동부 02",testResult.getPart2Motor(), testResult.getPart2Blade(), testResult.getPart2Esc(), findService.getPart2Point(testResult));
        droneScoreResponses.add(part2Score);
        DroneScoreResponse part3Score = DroneScoreResponse.createDroneScoreResponse("03","구동부 03",testResult.getPart3Motor(), testResult.getPart3Blade(), testResult.getPart3Esc(), findService.getPart3Point(testResult));
        droneScoreResponses.add(part3Score);
        DroneScoreResponse part4Score = DroneScoreResponse.createDroneScoreResponse("04","구동부 04",testResult.getPart4Motor(), testResult.getPart4Blade(), testResult.getPart4Esc(), findService.getPart4Point(testResult));
        droneScoreResponses.add(part4Score);
        return droneScoreResponses;
    }

    private DroneTestResultResponse createDroneTestResultResponse(TestResult testResult){
        return DroneTestResultResponse.of(createTotalScoreResponse(testResult),
                findService.getPoint(testResult),
                createDroneScoreResponses(testResult));
    }
    private TestResult findTestResultByDroneIdAndDate(Long droneId, int year, int month, int day){
        LocalDateTime localDateTime = LocalDateTime.of(year, month, day, 0, 0, 0);

        // 변환된 LocalDate를 사용하여 Repository 메서드 호출
        return testResultRepository.findByDroneIdAndCreateDateYearAndCreateDateMonthAndCreateDateDay(droneId, localDateTime.getYear(), localDateTime.getMonthValue(),localDateTime.getDayOfMonth()).orElseThrow(()->new EntityNotFoundException(ErrorCode.TEST_RESULT_NOT_FOUND));
    }
    private DroneTestInfoResponse createDroneTestInfoResponse(Drone drone){
        List<TestResult> testResults = testResultRepository.findAllByDroneId(drone.getId());
        List<String> testDates= testResults.stream().map(testResult->testResult.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"))).collect(Collectors.toList());
        return DroneTestInfoResponse.of(drone,testDates);
    }
}
