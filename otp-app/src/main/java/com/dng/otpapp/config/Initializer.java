package com.dng.otpapp.config;

import config.LoadConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import utils.log.AppLog;

import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

@Component
@Slf4j
public class Initializer implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        String str = String.format("Application Start {%s}",
                            LocalDateTime.now()
                                         .format(DateTimeFormatter.ISO_DATE));
        AppLog.messages().info(str);
        AppLog.cache().info(str);
        AppLog.error().info(str);
        AppLog.monitor().info(str);
    }
}
