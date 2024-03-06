package com.weflo.backend.domain.drone.service;

import com.weflo.backend.domain.cost.dto.ComponentCostAvgTimeLine;
import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.domain.DroneGroup;
import com.weflo.backend.domain.drone.dto.response.onBoarding.DroneGroupAvgResponse;
import com.weflo.backend.domain.drone.dto.response.onBoarding.DroneGroupInfoDetailResponse;
import com.weflo.backend.domain.drone.dto.response.onBoarding.DroneGroupInfoResponse;
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
    public DroneGroupInfoResponse getDroneGroupInfo(Long groupId, int year){
        DroneGroup droneGroup = findService.findDroneGroupById(groupId);
        DroneGroupInfoDetailResponse droneGroupInfoDetail = createDroneGroupInfoDetail(droneGroup);
        List<ComponentCostAvgTimeLine> componentCostAvgTimeLines = createComponentCostAvgTimeLines(groupId, year);
        return DroneGroupInfoResponse.of(droneGroupInfoDetail, componentCostAvgTimeLines);
    }
    public DroneGroupAvgResponse getDroneGroupAvg(Long groupId, int year){


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
            List<TestResult> testResults = testResultRepository.findByDroneGroupIdAndYearAndMonth(drone.getId(),year,month);
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
