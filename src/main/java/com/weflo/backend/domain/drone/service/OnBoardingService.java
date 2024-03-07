package com.weflo.backend.domain.drone.service;

import com.weflo.backend.domain.cost.dto.ComponentCostAvgTimeLine;
import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.domain.DroneGroup;
import com.weflo.backend.domain.drone.dto.request.DroneGroupRequest;
import com.weflo.backend.domain.drone.dto.response.onBoarding.*;
import com.weflo.backend.domain.drone.repository.DroneGroupInfoRepository;
import com.weflo.backend.domain.testresult.domain.TestResult;
import com.weflo.backend.domain.testresult.repository.TestResultRepository;
import com.weflo.backend.global.common.service.FindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OnBoardingService {
    private final DroneGroupInfoRepository droneGroupInfoRepository;
    private final FindService findService;
    private final TestResultRepository testResultRepository;
    public DroneGroupInfoResponse getDroneGroupInfo(DroneGroupRequest droneGroupRequest){
        DroneGroup droneGroup = findService.findDroneGroupById(droneGroupRequest.getGroupId());
        DroneGroupInfoDetailResponse droneGroupInfoDetail = createDroneGroupInfoDetail(droneGroup);
        List<ComponentCostAvgTimeLine> componentCostAvgTimeLines = createComponentCostAvgTimeLines(droneGroupRequest.getGroupId(), droneGroupRequest.getYear());
        return DroneGroupInfoResponse.of(droneGroupInfoDetail, componentCostAvgTimeLines);
    }
    public DroneGroupAvgResponse getDroneGroupAvg(DroneGroupRequest droneGroupRequest){
        List<Drone> drones = droneGroupInfoRepository.findAllDroneByDroneGroupId(droneGroupRequest.getGroupId());
        DroneGroupStateResponse droneGroupStateResponse = createDroneGroupStateResponse(droneGroupRequest.getGroupId(), drones);
        List<DroneGroupAvgTimeLineResponse> droneGroupAvgTimeLineResponses = createDroneGroupAvgScoreResponses(drones,droneGroupRequest.getYear());
        return DroneGroupAvgResponse.of(droneGroupStateResponse, droneGroupAvgTimeLineResponses);
    }
    private List<DroneGroupAvgTimeLineResponse> createDroneGroupAvgScoreResponses(List<Drone> drones, int year){
        List<DroneGroupAvgTimeLineResponse> droneGroupAvgTimeLineResponses = new ArrayList<>();
        for(int month=1;month<13; month++){
            DroneGroupAvgTimeLineResponse droneGroupAvgTimeLineResponse = createDroneGroupAvgTimeLine(drones,year,month);
            droneGroupAvgTimeLineResponses.add(droneGroupAvgTimeLineResponse);
        }
        return droneGroupAvgTimeLineResponses;
    }
    private DroneGroupAvgTimeLineResponse createDroneGroupAvgTimeLine(List<Drone> drones, int year, int month){
        int groupMonthAvgScore=0;
        for(Drone drone : drones){
            List<TestResult> testResults = testResultRepository.findByDroneIdAndCreateDateYearAndCreateDateMonth(drone.getId(),year,month);
            groupMonthAvgScore = getMonthAvgScore(testResults,groupMonthAvgScore);
        }
        if(groupMonthAvgScore!=0) {
            groupMonthAvgScore = groupMonthAvgScore/drones.size();
        }
        return DroneGroupAvgTimeLineResponse.of(month,groupMonthAvgScore);
    }
    private DroneGroupStateResponse createDroneGroupStateResponse(Long groupId,List<Drone> drones){
        int avgScore =0;
        for(Drone drone : drones){
            avgScore = avgScore+drone.getCost();
        }
        return DroneGroupStateResponse.of(avgScore/drones.size());
    }
    private List<ComponentCostAvgTimeLine> createComponentCostAvgTimeLines(Long groupId, int year){
        List<ComponentCostAvgTimeLine> componentCostAvgTimeLines = new ArrayList<>();
        List<Drone> drones = droneGroupInfoRepository.findAllDroneByDroneGroupId(groupId);
        for(int month=1;month<13; month++){
            ComponentCostAvgTimeLine componentCostAvgTimeLine = createComponentCostAvgTimeLine(drones,year,month);
            componentCostAvgTimeLines.add(componentCostAvgTimeLine);
        }
        return componentCostAvgTimeLines;
    }
    private ComponentCostAvgTimeLine createComponentCostAvgTimeLine(List<Drone> drones,int year, int month){
        int groupMonthCost=0;
        int totalMonthCost=0;
        for(Drone drone : drones){
            List<TestResult> testResults = testResultRepository.findByDroneIdAndCreateDateYearAndCreateDateMonth(drone.getId(),year,month);
            groupMonthCost = getMonthCost(testResults,groupMonthCost);
        }
        List<TestResult> allTestResults = testResultRepository.findAll();
        totalMonthCost = getMonthCost(allTestResults,totalMonthCost);
        return ComponentCostAvgTimeLine.of(month,totalMonthCost,groupMonthCost);
    }
    private int getMonthCost(List<TestResult> testResults, int monthCost){
        for(TestResult testResult : testResults){
            monthCost = testResult.getTotalCost()+monthCost;
        }
        return monthCost;
    }
    private int getMonthAvgScore(List<TestResult> testResults, int groupMonthAvgScore){
        for(TestResult testResult : testResults){
            groupMonthAvgScore = testResult.getPoint()+groupMonthAvgScore;
        }
        if(groupMonthAvgScore!=0) {
            groupMonthAvgScore = groupMonthAvgScore/testResults.size();
        }
        return groupMonthAvgScore;
    }
    private DroneGroupInfoDetailResponse createDroneGroupInfoDetail(DroneGroup droneGroup){
        int droneCount = getDroneCount(droneGroup.getId());
        int flightCount = getFlightCount(droneGroup.getId());
        return DroneGroupInfoDetailResponse.of(droneCount, flightCount,droneGroup.getCreateDate());
    }
    private int getFlightCount(Long groupId){
        int flightCount=0;
        List<Drone> drones = droneGroupInfoRepository.findAllDroneByDroneGroupId(groupId);
        for(Drone drone : drones){
            flightCount = drone.getFlightCount()+flightCount;
        }
        return flightCount;
    }
    private int getDroneCount(Long groupId){
        return droneGroupInfoRepository.countByDroneGroupId(groupId);
    }
}
