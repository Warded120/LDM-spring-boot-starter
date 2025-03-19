package com.ivan.softserve.ldm.config;

import com.ivan.softserve.ldm.constant.AppConstant;
import com.ivan.softserve.ldm.constant.ErrorMessage;
import com.ivan.softserve.ldm.exception.exceptions.FunctionalityNotAvailableException;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Lazy
@ConditionalOnMissingBean(DotenvConfig.class)
public class DotenvConfig {
    @Bean
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