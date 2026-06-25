/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

package ch.admin.bj.swiyu.registry.identifier.data.infrastructure.web.config;

import ch.admin.bj.swiyu.registry.identifier.data.Application;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {

    private final BuildProperties buildProperties;

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
            .info(
                new Info()
                    .title("Identifier Registry Data Service")
                    .description("IF-003 - APIs for the Identifier Registry Data Services")
                    .version(buildProperties.getVersion())
            );
    }

    @Bean
    GroupedOpenApi api() {
        return GroupedOpenApi.builder().group("API").packagesToScan(Application.class.getPackageName()).build();
    }
}
