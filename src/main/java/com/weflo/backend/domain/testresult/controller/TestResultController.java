package com.weflo.backend.domain.testresult.controller;

import com.weflo.backend.domain.component.dto.ComponentResponse;
import com.weflo.backend.domain.component.dto.DroneComponentResponse;
import com.weflo.backend.domain.testresult.dto.TestResultDateResponse;
import com.weflo.backend.domain.testresult.dto.TestResultTopSectionResponse;
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
                    + "견적서 날짜들을 조회할 때는 쿼리 파라미터로 아무것도 넘기지 않으면 됩니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "요청이 성공했습니다."
    )
    @GetMapping("/drones/{droneId}/test-results")
    public ResponseEntity<SuccessResponse<?>> getTestResultByDroneIdAndDate(
            @PathVariable(value = "droneId") Long droneId,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "day", required = false) Integer day,
            @RequestParam(value = "mode", required = false) String mode) {

        if (year == null || month == null || day == null) {
            List<TestResultDateResponse> dateResponses = testResultService.getTestResultDates(droneId);
            return SuccessResponse.ok(dateResponses);
        }

        if ("TOP-SECTION".equals(mode)) {
            LocalDateTime start = LocalDateTime.of(year, month, day, 0, 0, 1);
            LocalDateTime end = LocalDateTime.of(year, month, day, 23, 59, 59);
            List<DroneComponentResponse> responses = testResultService.getTestResultComponents(droneId, start, end);
            TestResultTopSectionResponse testResultTopSectionResponse = testResultService.generateTopSectionResponse(
                    responses);

            return SuccessResponse.ok(testResultTopSectionResponse);
        }

        if (mode == null) {
            LocalDateTime start = LocalDateTime.of(year, month, day, 1, 0, 0);
            LocalDateTime end = LocalDateTime.of(year, month, day, 23, 59, 59);
            List<DroneComponentResponse> responses = testResultService.getTestResultComponents(droneId, start, end);

            return SuccessResponse.ok(responses);
        }

        return null;

    }
}
