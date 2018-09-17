package com.reporting.iris.irissearch.utils;

import java.util.Map;

public interface FileWriteUtil {

    void writeStatusCountToFile(Map<String, Integer> customerStatusCountsMap);
}
