package com.weflo.backend.domain.drone.controller;

import com.weflo.backend.domain.drone.dto.request.DashBoardDetailRequest;
import com.weflo.backend.domain.drone.dto.request.SearchDroneRequest;
import com.weflo.backend.domain.drone.dto.response.DroneDetailResponse;
import com.weflo.backend.domain.drone.dto.response.dashBoardDetail.DashBoardDetailResponse;
import com.weflo.backend.domain.drone.dto.response.onBoarding.SearchDroneResponse;
import com.weflo.backend.domain.drone.service.DashBoardDetailService;
import com.weflo.backend.domain.drone.service.DashBoardService;
import com.weflo.backend.domain.drone.service.DroneService;
import com.weflo.backend.domain.drone.service.DroneGroupService;
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
    private final DroneGroupService droneGroupService;
    @GetMapping("/detail")
    public ResponseEntity<SuccessResponse<?>> getDroneDetail(@RequestParam(value = "droneId") Long droneId){
        final DroneDetailResponse droneDetailResponse = dashBoardService.getDroneDetail(droneId);
        return SuccessResponse.ok(droneDetailResponse);
    }

    @PostMapping("/dashboard/detail")
    public ResponseEntity<SuccessResponse<?>> getDroneDashBoardDetail(@RequestBody DashBoardDetailRequest dashBoardDetailRequest){
        final DashBoardDetailResponse dashBoardDetailResponse = dashBoardDetailService.getDashBoardDetail(dashBoardDetailRequest);
        return SuccessResponse.ok(dashBoardDetailResponse);
    }
    @PostMapping("/search")
    public ResponseEntity<SuccessResponse<?>> searchDrone(@RequestBody SearchDroneRequest searchDroneRequest){
        final List<SearchDroneResponse> searchDroneResponses = droneService.searchDrone(searchDroneRequest);
        return SuccessResponse.ok(searchDroneResponses);
    }
}
