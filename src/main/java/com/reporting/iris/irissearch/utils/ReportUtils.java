package com.reporting.iris.irissearch.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

@Component
public class ReportUtils {

    public static String loadQueryFromFile(@NonNull final String fileName) throws IOException {
        Resource resource = new ClassPathResource("sql/" + fileName);
        return StreamUtils.copyToString(resource.getInputStream(), Charset.defaultCharset());
    }
}
