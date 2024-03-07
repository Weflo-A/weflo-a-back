package com.weflo.backend.domain.testresult.controller;

import com.weflo.backend.domain.component.dto.ComponentResponse;
import com.weflo.backend.domain.component.dto.DroneComponentResponse;
import com.weflo.backend.domain.testresult.service.TestResultService;
import com.weflo.backend.global.common.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "견적서 관련 API", description = "견적서 관련 API")
public class TestResultController {

    private final TestResultService testResultService;

    @Operation(
            summary = "견적서 조회 API",
            description = "날짜와 드론 PK를 기준으로 견적서를 조회 합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "요청이 성공했습니다."
    )
    @GetMapping("/drones/{droneId}/test-results")
    public ResponseEntity<SuccessResponse<?>> getTestResult(
            @PathVariable(value = "droneId") Long droneId,
            @RequestParam("year") int year,
            @RequestParam("month") int month,
            @RequestParam("day") int day) {

        LocalDateTime start = LocalDateTime.of(year, month, day, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(year, month, day, 23, 59, 59);
        List<DroneComponentResponse> responses = testResultService.getTestResultComponents(droneId, start, end);

        return SuccessResponse.ok(responses);
    }

}
