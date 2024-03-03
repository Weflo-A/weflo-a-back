package com.weflo.backend.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "weflo-A API 명세서",
                description = "weflo-A API 명세서",
                version = "v1"))

@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi allAPI() {
        String[] paths = {"/api/**"};

        return GroupedOpenApi.builder()
                .group("모든-API")  // 그룹 이름을 설정한다.
                .pathsToMatch(paths)     // 그룹에 속하는 경로 패턴을 지정한다.
                .build();
    }

    @Bean
    public GroupedOpenApi droneAPI() {
        String[] paths = {"/api/drones/**"};

        return GroupedOpenApi.builder()
                .group("드론-API")  // 그룹 이름을 설정한다.
                .pathsToMatch(paths)     // 그룹에 속하는 경로 패턴을 지정한다.
                .build();
    }

    @Bean
    public GroupedOpenApi droneComponentAPI() {
        String[] paths = {"/api/drone-components/**"};

        return GroupedOpenApi.builder()
                .group("드론부품-API")  // 그룹 이름을 설정한다.
                .pathsToMatch(paths)     // 그룹에 속하는 경로 패턴을 지정한다.
                .build();
    }

    @Bean
    public GroupedOpenApi componentAPI() {
        String[] paths = {"/api/components/**"};

        return GroupedOpenApi.builder()
                .group("부품-API")  // 그룹 이름을 설정한다.
                .pathsToMatch(paths)     // 그룹에 속하는 경로 패턴을 지정한다.
                .build();
    }

    @Bean
    public GroupedOpenApi testResultAPI() {
        String[] paths = {"/api/test-results/**"};

        return GroupedOpenApi.builder()
                .group("검사결과-API")  // 그룹 이름을 설정한다.
                .pathsToMatch(paths)     // 그룹에 속하는 경로 패턴을 지정한다.
                .build();
    }
}
