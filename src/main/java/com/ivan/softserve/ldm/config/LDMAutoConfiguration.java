package com.ivan.softserve.ldm.config;

import com.ivan.softserve.ldm.controller.LogFileController;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.ComponentScan;

@AutoConfiguration
@ConditionalOnClass(LogFileController.class)
@ComponentScan(basePackageClasses = {LogFileController.class})
public class LDMAutoConfiguration {
}
