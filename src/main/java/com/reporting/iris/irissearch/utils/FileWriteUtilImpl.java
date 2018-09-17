package com.reporting.iris.irissearch.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Component
public class FileWriteUtilImpl implements FileWriteUtil {


    @Value("${customer.base.dir}")
    private String customerBaseDir;

    @Value("${iris.short.name}")
    private String customerShortName;

    @Value("${iris.start.date}")
    private String startDate;

    @Value("${iris.end.date}")
    private String endDate;

    private final LocalDateTime currentTime = LocalDateTime.now();

    private final DateUtil dateUtil;
    private final CustomerToCustomerShortNameMap customerShortNameMap;

    public FileWriteUtilImpl(DateUtil dateUtil, CustomerToCustomerShortNameMap customerShortNameMap) {
        this.dateUtil = dateUtil;
        this.customerShortNameMap = customerShortNameMap;
    }

    /**
     * @param customerStatusCountsMap
     */
    @Override
    public void writeStatusCountToFile(Map<String, Integer> customerStatusCountsMap) {

        String appendedFileNameWithFileExtension = "_JOBCOUNT.txt";


        try (PrintWriter pw = new PrintWriter(
            new FileWriter(
                new File(
                    // workingDirectory
                    createJobCountDirectory() + File.separator

                        //filename
                        + dateUtil.localDateTimeToString(currentTime)
                        + "_" + customerShortNameMap.getCustomerShortNameMap().get(customerShortName)
                        + "_" + appendedFileNameWithFileExtension)))) {

            pw.printf("For Client %s and Date Range '%s - %s':\n", customerShortName, startDate, endDate);
            pw.printf("%-15s%8d \n", "Total Jobs: ",
                customerStatusCountsMap.getOrDefault("AVAILABLE", 0)
                    + customerStatusCountsMap.getOrDefault("WORKING", 0)
                    + customerStatusCountsMap.getOrDefault("COMPLETE", 0)
                    + customerStatusCountsMap.getOrDefault("FAILED", 0)
            );

            pw.printf("%-15s%8d \n", "Available Jobs:", customerStatusCountsMap.getOrDefault("AVAILABLE", 0));
            pw.printf("%-15s%8d \n", "Working Jobs:", customerStatusCountsMap.getOrDefault("WORKING", 0));
            pw.printf("%-15s%8d \n", "Complete Jobs:", customerStatusCountsMap.getOrDefault("COMPLETE", 0));
            pw.printf("%-15s%8d \n", "Failed Jobs:", customerStatusCountsMap.getOrDefault("FAILED", 0));
            pw.print("\n");
            pw.print("\n");
            pw.print("\n");
            pw.print("\n");
            pw.print("\n");
            pw.print("\n");
            pw.print("\n");
//            pw.print("The following files were created:\n");
//            pw.printf("./Job_Counts/%s_%s-%s_JOBCOUNT.txt\n", startDate, endDate, customerShortName);
//            pw.printf("./Full_Reports/%s_%s-%s_FULL_REPORT.txt\n", startDate, endDate, customerShortName);
//            pw.printf("https://prodreports.recondohealth.net/clients/NewReports/%s/%s/\n", "averahospital", "run-date");

            pw.flush();
            log.info("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    private boolean createJobCountDirectory() {
        String JOB_COUNT_FILE_LOCATION = "Job_Counts";
        return createDirectory(JOB_COUNT_FILE_LOCATION);
    }

    private static boolean createDirectory(String directoryLocation) {
        boolean validLocation = true;

        File outDirectory = new File(directoryLocation);

        if (!outDirectory.exists() || !outDirectory.isDirectory()) {
            log.debug("Out directory: \"" + directoryLocation + "\" does not exist.");

            final boolean mkdirs = outDirectory.mkdirs();

            if (!outDirectory.exists() || !outDirectory.isDirectory()) {
                log.error("Unabled to create out directory: \"" + directoryLocation + "\".");
                validLocation = false;
            } else {
                log.debug("Created out directory: \"" + directoryLocation + "\".");
            }
        } else {
            //JobCountDirectory already exists
            log.debug("JobCountDirectory already exists. nothing to be done.");
        }
        return validLocation;
    }

}


