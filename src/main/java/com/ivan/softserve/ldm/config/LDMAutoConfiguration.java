package com.ivan.softserve.ldm.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@AutoConfiguration
@ComponentScan(basePackages = "com.ivan.softserve.ldm.controller")
public class LDMAutoConfiguration {
}
