package com.reporting.iris.irissearch.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class CustomerStatusCount {

    private String customer;
    private String status;
    private Integer count;

}
