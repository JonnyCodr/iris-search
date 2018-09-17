package com.reporting.iris.irissearch.services;

import com.reporting.iris.irissearch.models.CustomerStatusCount;
import com.reporting.iris.irissearch.repositories.IrisRepository;
import com.reporting.iris.irissearch.utils.FileWriteUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerStatusCountServiceImpl implements CustomerStatusCountService {

    @Value("${iris.short.name}")
    private String custShortName;

    @Value("${iris.start.date}")
    private String startDate;

    @Value("${iris.end.date}")
    private String endDate;


    private final IrisRepository irisRepository;
    private final FileWriteUtil fileWriteUtil;

    public CustomerStatusCountServiceImpl(IrisRepository irisRepository, FileWriteUtil fileWriteUtil) {
        this.irisRepository = irisRepository;
        this.fileWriteUtil = fileWriteUtil;
    }

    @Override
    public String generate() {

        StringBuilder sb = new StringBuilder();

        List<CustomerStatusCount> customerStatusCounts = queryForResults();
        sb.append(customerStatusCounts.get(1).getCustomer());
        sb.append("                      ");
        sb.append(startDate);
        sb.append("\n=======================================\n");
        for (CustomerStatusCount c : customerStatusCounts) {

            sb.append(c.getStatus());
            sb.append(" ");
            sb.append(c.getCount());
            sb.append("\n");
        }

        Map<String, Integer> customerStatusCountsMap = customerStatusCounts.stream().collect(
            Collectors.toMap(CustomerStatusCount::getStatus, CustomerStatusCount::getCount));

        fileWriteUtil.writeStatusCountToFile(customerStatusCountsMap);

        return sb.toString();
    }

    private List<CustomerStatusCount> queryForResults() {
        List<CustomerStatusCount> customerStatusCounts = new ArrayList<>();

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("customer_short_name", custShortName)
                .addValue("start_date", startDate)
                .addValue("end_date", endDate);

        try {
            customerStatusCounts = irisRepository.queryForCustomerStatusCount(params);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customerStatusCounts;
    }
}
