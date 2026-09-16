package com.stepia.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.rewritePath;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class GatewayRoutesConfig {

        @Value("${gateway.env.core-service-url}")
        private String coreServiceUrl;

        @Value("${gateway.env.document-service-url}")
        private String documentServiceUrl;

        @Value("${gateway.env.ia-service-url}")
        private String iaServiceUrl;

        @Bean
        public RouterFunction<ServerResponse> coreServiceRoute() {

                return route("core-service")
                                .route(
                                                path("/api/core-service/**"),
                                                http())
                                .before(uri(coreServiceUrl))
                                .before(rewritePath(
                                                "/api/core-service/(?<segment>.*)",
                                                "/${segment}"))
                                .build();
        }

        @Bean
        public RouterFunction<ServerResponse> documentServiceRoute() {

                return route("document-service")
                                .route(
                                                path("/api/document-service/**"),
                                                http())
                                .before(uri(documentServiceUrl))
                                .before(rewritePath(
                                                "/api/document-service/(?<segment>.*)",
                                                "/${segment}"))
                                .build();
        }

        @Bean
        public RouterFunction<ServerResponse> iaServiceRoute() {

                return route("ia-service")
                                .route(
                                                path("/api/ia-service/**"),
                                                http())
                                .before(uri(iaServiceUrl))
                                .before(rewritePath(
                                                "/api/ia-service/(?<segment>.*)",
                                                "/${segment}"))
                                .build();
        }
}