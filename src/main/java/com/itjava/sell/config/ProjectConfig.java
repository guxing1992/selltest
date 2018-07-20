package com.itjava.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Data
@ConfigurationProperties(prefix = "projecturl")
@Component
public class ProjectConfig {
    private String sell;
}
