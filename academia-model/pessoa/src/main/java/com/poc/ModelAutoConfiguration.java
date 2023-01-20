package com.poc;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ModelAutoConfiguration.class)
public class ModelAutoConfiguration {
}
