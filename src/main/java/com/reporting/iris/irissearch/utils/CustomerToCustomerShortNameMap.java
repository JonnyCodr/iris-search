package com.reporting.iris.irissearch.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
public class CustomerToCustomerShortNameMap {

    @Value("#{${customer.short.name.map}}")
    private Map<String, String> customerShortNameMap;
}
