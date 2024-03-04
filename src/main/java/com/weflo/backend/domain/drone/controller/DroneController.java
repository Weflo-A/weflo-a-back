package com.weflo.backend.domain.drone.controller;

import com.weflo.backend.domain.drone.dto.response.DroneDetailResponse;
import com.weflo.backend.domain.drone.service.DashBoardService;
import com.weflo.backend.global.common.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/drone")
@RestController
public class DroneController {
    private final DashBoardService dashBoardService;

    @GetMapping("/detail")
    public ResponseEntity<SuccessResponse<?>> getDroneDetail(@RequestParam Long droneId){
        final DroneDetailResponse droneDetailResponse = dashBoardService.getDroneDetail(droneId);
        return SuccessResponse.ok(droneDetailResponse);
    }
}
