package com.ivan.softserve.ldm.config;

import com.ivan.softserve.ldm.constant.AppConstant;
import com.ivan.softserve.ldm.constant.ErrorMessage;
import com.ivan.softserve.ldm.exception.exceptions.FunctionalityNotAvailableException;
import com.ivan.softserve.ldm.service.dotenv.DotenvService;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@AutoConfiguration
//@ComponentScan(basePackages = "com.ivan.softserve.ldm.controller")
public class LDMAutoConfiguration {
    @Bean("LDMdotenv")
    Dotenv dotenv() {
        try {
            return Dotenv.configure()
                    .filename(AppConstant.DOTENV_FILENAME)
                    .load();
        } catch (DotenvException ex) {
            throw new FunctionalityNotAvailableException(ErrorMessage.FUNCTIONALITY_NOT_AVAILABLE);
        }
    }
}
