package com.reporting.iris.irissearch;

import com.reporting.iris.irissearch.services.CustomerStatusCountService;
import com.reporting.iris.irissearch.utils.EmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class IrisSearchApplication implements ApplicationRunner {

    @Value("${iris.short.name}")
    private String customerShortName;



    @Value("${iris.start.date ?: '1970-01-01'}")
    private String startDate;

    @Value("${iris.end.date ?: '2050-01-01'}")
    private String endDate;

    @Autowired
    private CustomerStatusCountService customerStatusCountService;

    public static void main(String[] args) {
        SpringApplication.run(IrisSearchApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("Application started with command-line arguments: {}", Arrays.toString(args.getSourceArgs()));
        log.info("NonOptionArgs: {}", args.getNonOptionArgs());
        log.info("OptionNames: {}", args.getOptionNames());

//        for (String name : args.getOptionNames()){
//            log.info("arg-" + name + "=" + args.getOptionValues(name));
//        }

        if (!args.containsOption("iris.short.name")) {
            throw new Exception("Customer Short Name Is A Required Param, and can be added with '--iris.short.name=[value]'");
        }

        log.info(customerShortName);

        if (!args.containsOption("iris.start.date")) {
            log.info("Start Date is optional, and can be added with '--iris.start.date=YYYY-MM-dd'");

        } else {
            log.info(startDate);
        }

        if (!args.containsOption("iris.end.date")) {
            log.info("End Date is optional, and can be added with '--iris.end.date=YYYY-MM-dd'");

        } else {
            log.info(endDate);
        }

        final String generate = customerStatusCountService.generate();

    }
}
