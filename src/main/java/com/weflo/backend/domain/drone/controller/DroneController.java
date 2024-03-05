package com.weflo.backend.domain.drone.controller;

import com.weflo.backend.domain.drone.dto.request.DashBoardDetailRequest;
import com.weflo.backend.domain.drone.dto.response.DroneDetailResponse;
import com.weflo.backend.domain.drone.dto.response.dashBoardDetail.DashBoardDetailResponse;
import com.weflo.backend.domain.drone.service.DashBoardDetailService;
import com.weflo.backend.domain.drone.service.DashBoardService;
import com.weflo.backend.global.common.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/drone")
@RestController
public class DroneController {
    private final DashBoardService dashBoardService;
    private final DashBoardDetailService dashBoardDetailService;

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
}
