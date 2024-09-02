package com.example.jinxun.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger3Config
{
    @Bean
    public GroupedOpenApi department()
    {
        return GroupedOpenApi.builder().group("部门管理模块").pathsToMatch("/department/**").build();
    }

    @Bean
    public GroupedOpenApi workOrder()
    {
        return GroupedOpenApi.builder().group("工单管理模块").pathsToMatch("/order/**").build();
    }


}

