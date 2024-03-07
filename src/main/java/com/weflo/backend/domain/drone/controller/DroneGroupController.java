package com.weflo.backend.domain.drone.controller;

import com.weflo.backend.domain.drone.dto.request.DroneGroupRequest;
import com.weflo.backend.domain.drone.dto.response.DroneGroupListResponse;
import com.weflo.backend.domain.drone.dto.response.onBoarding.DroneGroupAvgResponse;
import com.weflo.backend.domain.drone.dto.response.onBoarding.DroneGroupInfoResponse;
import com.weflo.backend.domain.drone.service.DroneGroupService;
import com.weflo.backend.domain.drone.service.OnBoardingService;
import com.weflo.backend.global.common.SuccessResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DroneGroupController {
    private final DroneGroupService droneGroupService;
    private final OnBoardingService onBoardingService;

    @GetMapping("/drone-groups/{droneGroupId}/drones")
    public ResponseEntity<SuccessResponse<?>> getDronesByGroup(@PathVariable(value = "droneGroupId") Long droneGroupId){
        List<DroneGroupListResponse> droneGroupListResponses = droneGroupService.getDronesByDroneGroup(droneGroupId);
        return SuccessResponse.ok(droneGroupListResponses);
    }
    @PostMapping("/drone-group/info")
    public ResponseEntity<SuccessResponse<?>> getDroneGroupInfo(@RequestBody DroneGroupRequest droneGroupRequest){
        DroneGroupInfoResponse droneGroupInfoResponse = onBoardingService.getDroneGroupInfo(droneGroupRequest);
        return SuccessResponse.ok(droneGroupInfoResponse);
    }
    @PostMapping("/drone-group/avg")
    public ResponseEntity<SuccessResponse<?>> getDroneGroupAvg(@RequestBody DroneGroupRequest droneGroupRequest){
        DroneGroupAvgResponse droneGroupAvgResponse = onBoardingService.getDroneGroupAvg(droneGroupRequest);
        return SuccessResponse.ok(droneGroupAvgResponse);
    }
}
