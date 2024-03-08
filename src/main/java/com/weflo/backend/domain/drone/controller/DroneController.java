package com.weflo.backend.domain.drone.controller;

import com.weflo.backend.domain.drone.dto.request.CreateDroneRequest;
import com.weflo.backend.domain.drone.dto.request.DashBoardDetailRequest;
import com.weflo.backend.domain.drone.dto.request.SearchDroneRequest;
import com.weflo.backend.domain.drone.dto.request.SortScoreListRequest;
import com.weflo.backend.domain.drone.dto.response.DroneDetailResponse;
import com.weflo.backend.domain.drone.dto.response.dashBoardDetail.DashBoardDetailResponse;
import com.weflo.backend.domain.drone.dto.response.dashBoardDetail.DroneScoreResponse;
import com.weflo.backend.domain.drone.dto.response.onBoarding.SearchDroneResponse;
import com.weflo.backend.domain.drone.service.*;
import com.weflo.backend.global.common.SuccessResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/drone")
@RestController
public class DroneController {
    private final DashBoardService dashBoardService;
    private final DashBoardDetailService dashBoardDetailService;
    private final DroneService droneService;
    private final OnBoardingService onBoardingService;
    @GetMapping("/detail")
    public ResponseEntity<SuccessResponse<?>> getDroneDetail(@RequestParam(value = "droneId") Long droneId){
        final DroneDetailResponse droneDetailResponse = dashBoardService.getDroneDetail(droneId);
        return SuccessResponse.ok(droneDetailResponse);
    }
    @PostMapping("/create")
    public ResponseEntity<SuccessResponse<?>> createDrone(@RequestBody CreateDroneRequest createDroneRequest){
        onBoardingService.createDrone(createDroneRequest);
        return SuccessResponse.created(null);
    }
    @PostMapping("/dashboard/detail")
    public ResponseEntity<SuccessResponse<?>> getDroneDashBoardDetail(@RequestBody DashBoardDetailRequest dashBoardDetailRequest){
        final DashBoardDetailResponse dashBoardDetailResponse = dashBoardDetailService.getDashBoardDetail(dashBoardDetailRequest);
        return SuccessResponse.ok(dashBoardDetailResponse);
    }
    @PostMapping("/sort")
    public ResponseEntity<SuccessResponse<?>> sortScoreList(@RequestBody SortScoreListRequest sortScoreListRequest){
        final List<DroneScoreResponse> droneScoreResponses = dashBoardDetailService.sortDroneScoreResponseList(sortScoreListRequest);
        return SuccessResponse.ok(droneScoreResponses);
    }
    @PostMapping("/search")
    public ResponseEntity<SuccessResponse<?>> searchDrone(@RequestBody SearchDroneRequest searchDroneRequest){
        final List<SearchDroneResponse> searchDroneResponses = droneService.searchDrone(searchDroneRequest);
        return SuccessResponse.ok(searchDroneResponses);
    }
}
