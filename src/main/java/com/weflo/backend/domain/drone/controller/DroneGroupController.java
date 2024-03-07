package com.weflo.backend.domain.drone.controller;

import com.weflo.backend.domain.drone.dto.request.DroneGroupRequest;
import com.weflo.backend.domain.drone.dto.request.DroneInfoListRequest;
import com.weflo.backend.domain.drone.dto.response.DroneGroupListResponse;
import com.weflo.backend.domain.drone.dto.response.onBoarding.DroneGroupAvgResponse;
import com.weflo.backend.domain.drone.dto.response.onBoarding.DroneGroupInfoResponse;
import com.weflo.backend.domain.drone.dto.response.onBoarding.DroneGroupNameResponse;
import com.weflo.backend.domain.drone.dto.response.onBoarding.DroneSimpleInfoResponse;
import com.weflo.backend.domain.drone.service.DroneGroupService;
import com.weflo.backend.domain.drone.service.OnBoardingService;
import com.weflo.backend.global.common.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drone-group")
@RequiredArgsConstructor
@Tag(name = "드론 그룹 관련 API", description = "드론 그룹 API")
public class DroneGroupController {
    private final DroneGroupService droneGroupService;
    private final OnBoardingService onBoardingService;

    @Operation(
            summary = "드론 그룹 내 드론 조회 API",
            description = "드론 그룹에 속한 드론을 조회합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "요청이 성공했습니다."
    )

    @GetMapping("/drone-group/{droneGroupId}/drones")
    public ResponseEntity<SuccessResponse<?>> getDronesByGroup(@PathVariable(value = "droneGroupId") Long droneGroupId){
        DroneGroupListResponse droneGroupListResponses = droneGroupService.getDronesByDroneGroup(droneGroupId);
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
