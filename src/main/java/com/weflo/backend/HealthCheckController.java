package com.weflo.backend;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "healthCheck", description = "상태 체크 API")
@RequestMapping("/api")
public class HealthCheckController {

    @Operation(
            summary = "health check",
            description = "서버 상태를 체크합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "서버가 정상 작동 중입니다."
    )
    @GetMapping("/health-check")
    public String healthCheck() {
        return "health Check success";
    }
}
