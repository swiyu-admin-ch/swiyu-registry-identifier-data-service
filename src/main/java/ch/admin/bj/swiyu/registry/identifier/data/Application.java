/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

package ch.admin.bj.swiyu.registry.identifier.data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        var env = SpringApplication.run(Application.class, args).getEnvironment();
        var appName = env.getProperty("spring.application.name");
        var serverPort = env.getProperty("server.port", "8080");
        var scheme = env.getProperty("server.ssl.key-store") != null ? "https" : "http";

        log.info(
            """

            ----------------------------------------------------------------------------
            \t'{}' is running!\s
            \tProfile(s): \t\t\t\t{}
            \tSwaggerUI:   \t\t\t\t{}://localhost:{}/swagger-ui.html
            ----------------------------------------------------------------------------""",
            appName,
            env.getActiveProfiles(),
            scheme,
            serverPort
        );
    }
}
