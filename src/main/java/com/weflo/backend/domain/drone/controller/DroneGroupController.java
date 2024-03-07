package com.weflo.backend.domain.drone.controller;

import com.weflo.backend.domain.drone.domain.DroneGroup;
import com.weflo.backend.domain.drone.dto.request.DroneGroupRequest;
import com.weflo.backend.domain.drone.dto.request.DroneInfoListRequest;
import com.weflo.backend.domain.drone.dto.response.DroneGroupListResponse;
import com.weflo.backend.domain.drone.dto.response.onBoarding.DroneGroupAvgResponse;
import com.weflo.backend.domain.drone.dto.response.onBoarding.DroneGroupInfoResponse;
import com.weflo.backend.domain.drone.dto.response.onBoarding.DroneGroupNameResponse;
import com.weflo.backend.domain.drone.dto.response.onBoarding.DroneSimpleInfoResponse;
import com.weflo.backend.domain.drone.repository.DroneGroupRepository;
import com.weflo.backend.domain.drone.service.DroneGroupService;
import com.weflo.backend.domain.drone.service.OnBoardingService;
import com.weflo.backend.global.common.SuccessResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drone-group")
@RequiredArgsConstructor
public class DroneGroupController {
    private final DroneGroupService droneGroupService;
    private final OnBoardingService onBoardingService;

    @GetMapping("/{droneGroupId}/drones")
    public ResponseEntity<SuccessResponse<?>> getDronesByGroup(@PathVariable(value = "droneGroupId") Long droneGroupId){
        List<DroneGroupListResponse> droneGroupListResponses = droneGroupService.getDronesByDroneGroup(droneGroupId);
        return SuccessResponse.ok(droneGroupListResponses);
    }
    @PostMapping("/info")
    public ResponseEntity<SuccessResponse<?>> getDroneGroupInfo(@RequestBody DroneGroupRequest droneGroupRequest){
        DroneGroupInfoResponse droneGroupInfoResponse = onBoardingService.getDroneGroupInfo(droneGroupRequest);
        return SuccessResponse.ok(droneGroupInfoResponse);
    }
    @PostMapping("/avg")
    public ResponseEntity<SuccessResponse<?>> getDroneGroupAvg(@RequestBody DroneGroupRequest droneGroupRequest){
        DroneGroupAvgResponse droneGroupAvgResponse = onBoardingService.getDroneGroupAvg(droneGroupRequest);
        return SuccessResponse.ok(droneGroupAvgResponse);
    }
    @PostMapping("/drones")
    public ResponseEntity<SuccessResponse<?>> getDroneListFromDroneGroup(@RequestBody DroneInfoListRequest droneInfoListRequest){
        List<DroneSimpleInfoResponse> droneSimpleInfoResponses = onBoardingService.getDroneListFromDroneGroup(droneInfoListRequest);
        return SuccessResponse.ok(droneSimpleInfoResponses);
    }
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getDroneGroup(){
        List<DroneGroupNameResponse> droneGroups = onBoardingService.getDroneGroupNameList();
        return SuccessResponse.ok(droneGroups);
    }
}
