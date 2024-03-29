package com.weflo.backend.domain.drone.service;

import com.weflo.backend.domain.cost.dto.ComponentCostAvgTimeLine;
import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.domain.DroneGroup;
import com.weflo.backend.domain.drone.domain.DroneGroupInfo;
import com.weflo.backend.domain.drone.domain.DroneModel;
import com.weflo.backend.domain.drone.dto.request.CreateDroneGroupRequest;
import com.weflo.backend.domain.drone.dto.request.CreateDroneRequest;
import com.weflo.backend.domain.drone.dto.request.DroneGroupRequest;
import com.weflo.backend.domain.drone.dto.request.DroneInfoListRequest;
import com.weflo.backend.domain.drone.dto.response.onBoarding.*;
import com.weflo.backend.domain.drone.repository.DroneGroupInfoRepository;
import com.weflo.backend.domain.drone.repository.DroneGroupRepository;
import com.weflo.backend.domain.drone.repository.DroneRepository;
import com.weflo.backend.domain.testresult.domain.TestResult;
import com.weflo.backend.domain.testresult.repository.TestResultRepository;
import com.weflo.backend.global.common.service.FindService;
import com.weflo.backend.global.error.ErrorCode;
import com.weflo.backend.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.weflo.backend.domain.drone.domain.DroneModel.getEnumDroneModelFromStringModel;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class OnBoardingService {
    private final DroneGroupInfoRepository droneGroupInfoRepository;
    private final FindService findService;
    private final TestResultRepository testResultRepository;
    private final DroneGroupRepository droneGroupRepository;
    private final DroneRepository droneRepository;
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
    public List<DroneSimpleInfoResponse> getDroneListFromDroneGroup(DroneInfoListRequest droneInfoListRequest){
        List<Drone> drones = droneGroupInfoRepository.findAllDroneByDroneGroupId(droneInfoListRequest.getGroupId());
        DroneGroup droneGroup = findService.findDroneGroupById(droneInfoListRequest.getGroupId());
        List<DroneSimpleInfoResponse> droneSimpleInfoResponses = createDroneSimpleInfoResponses(drones,droneGroup);

        return sort(droneSimpleInfoResponses,droneInfoListRequest.getFilter());
    }
    public List<DroneGroupNameResponse> getDroneGroupNameList(){
        List<DroneGroup> droneGroups = droneGroupRepository.findAll();
        return droneGroups.stream().map(droneGroup -> DroneGroupNameResponse.of(droneGroup)).collect(Collectors.toList());
    }
    public void createDrone(CreateDroneRequest createDroneRequest){
        List<DroneGroup> droneGroups = new ArrayList<>();
        for(Long groupId : createDroneRequest.getGroupIds()) {
            DroneGroup droneGroup = findService.findDroneGroupById(groupId);
            droneGroups.add(droneGroup);
        }
        DroneModel droneModel = getEnumDroneModelFromStringModel(createDroneRequest.getModel());
        Drone drone = Drone.createDrone(createDroneRequest,droneModel);
        for(DroneGroup droneGroup : droneGroups) {
            DroneGroupInfo droneGroupInfo = DroneGroupInfo.createDroneGroupInfo(droneGroup, drone);
            droneGroupInfoRepository.save(droneGroupInfo);
        }
        droneRepository.save(drone);
    }
    public void addDroneToGroup(Long droneId, Long groupId){
        DroneGroup droneGroup = findService.findDroneGroupById(groupId);
        Drone drone = droneRepository.findById(droneId).orElseThrow(()->new EntityNotFoundException(ErrorCode.DRONE_NOT_FOUND));
        DroneGroupInfo droneGroupInfo = DroneGroupInfo.createDroneGroupInfo(droneGroup, drone);
        droneGroupInfoRepository.save(droneGroupInfo);
    }
    public void createDroneGroup(CreateDroneGroupRequest createDroneGroupRequest){
        DroneGroup droneGroup = DroneGroup.createDroneGroup(createDroneGroupRequest);
        droneGroupRepository.save(droneGroup);
    }
    private List<DroneSimpleInfoResponse> sort(List<DroneSimpleInfoResponse> droneSimpleInfoResponses, String filter){
        if ("cost".equals(filter)) {
            droneSimpleInfoResponses.sort(Comparator.comparingInt(DroneSimpleInfoResponse::getCost).reversed());
        } else if ("date".equals(filter)) {
            droneSimpleInfoResponses.sort(Comparator.comparing(DroneSimpleInfoResponse::getDate));
        } else if ("year".equals(filter)) {
            droneSimpleInfoResponses.sort(Comparator.comparingInt(DroneSimpleInfoResponse::getYear).reversed());
        }

        return droneSimpleInfoResponses;
    }
    private List<DroneSimpleInfoResponse> createDroneSimpleInfoResponses(List<Drone> drones,DroneGroup droneGroup){
        return drones.stream().map(drone -> DroneSimpleInfoResponse.of(drone,droneGroupInfoRepository.findByDroneIdAndDroneGroupId(drone.getId(), droneGroup.getId()).getCreateDate())).collect(Collectors.toList());
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
        int num =0;
        for(Drone drone : drones){
            List<TestResult> testResults = testResultRepository.findByDroneIdAndCreateDateYearAndCreateDateMonth(drone.getId(),year,month);
            if(!testResults.isEmpty()) {
                num++;
            }
            groupMonthAvgScore = groupMonthAvgScore+getMonthAvgScore(testResults);
        }
        if(groupMonthAvgScore!=0&&!drones.isEmpty()) {
            groupMonthAvgScore = groupMonthAvgScore/num;
        }
        return DroneGroupAvgTimeLineResponse.of(month,groupMonthAvgScore);
    }
    private DroneGroupStateResponse createDroneGroupStateResponse(Long groupId,List<Drone> drones){
        int avgScore =0;
        int num =0;
        for(Drone drone : drones){
            List<TestResult> testResults = testResultRepository.findAllByDroneId(drone.getId());
            if(!testResults.isEmpty())
                num++;
            avgScore = avgScore+avg(testResults);
        }
        if(avgScore!=0&&!drones.isEmpty()) {
            avgScore = avgScore/num;
        }
        return DroneGroupStateResponse.of(avgScore);
    }
    private int avg(List<TestResult> testResults){
        int avgScore =0;
        for(TestResult testResult : testResults) {
            avgScore = avgScore + findService.getPoint(testResult);
        }
        if(avgScore!=0&&!testResults.isEmpty()) {
            avgScore = avgScore/testResults.size();
        }
        return avgScore;
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
        int num=0;
        for(Drone drone : drones){
            List<TestResult> testResults = testResultRepository.findByDroneIdAndCreateDateYearAndCreateDateMonth(drone.getId(),year,month);
            if(!testResults.isEmpty())
                num++;
            groupMonthCost = groupMonthCost+getMonthCost(testResults);
        }
        List<TestResult> allTestResults = testResultRepository.findByCreateDateYearAndCreateDateMonth(year,month);
        totalMonthCost = getMonthCost(allTestResults);
        if(groupMonthCost!=0&&!drones.isEmpty()) {
            groupMonthCost = groupMonthCost/num;
        }
        return ComponentCostAvgTimeLine.of(month,totalMonthCost,groupMonthCost);
    }
    private int getMonthCost(List<TestResult> testResults){
        int monthCost=0;
        for(TestResult testResult : testResults){
            monthCost = testResult.getTotalCost()+monthCost;
        }
        if(monthCost!=0&&!testResults.isEmpty()) {
            monthCost = monthCost/testResults.size();
        }
        return monthCost;
    }
    private int getMonthAvgScore(List<TestResult> testResults){
        int groupMonthAvgScore = 0;
        for(TestResult testResult : testResults){
            groupMonthAvgScore = findService.getPoint(testResult)+groupMonthAvgScore;
        }
        if(groupMonthAvgScore!=0&&!testResults.isEmpty()) {
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
