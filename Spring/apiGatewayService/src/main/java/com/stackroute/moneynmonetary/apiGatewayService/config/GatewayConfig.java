package com.stackroute.moneynmonetary.apiGatewayService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
public class GatewayConfig {
    @Autowired
    AuthenticationFilter filter;
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("MoneynMonetaryCustomerService", r -> r.path("/api/v1/customers/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://MoneynMonetaryCustomerService"))

                 .route("MoneynMonetaryOtpService", r -> r.path("/api/v1/otp/**")
                         .filters(f -> f.filter(filter))
                         .uri("lb://MoneynMonetaryOtpService"))

                 .route("MoneynMonetaryAuthenticationService", r -> r.path("/api/v1/authentication/**")
                         .filters(f -> f.filter(filter))
                         .uri("lb://MoneynMonetaryAuthenticationService"))

                .route("MoneynMonetaryTransactionService", r -> r.path("/api/v1/payments/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://MoneynMonetaryTransactionService"))
                .build();
    }

}
