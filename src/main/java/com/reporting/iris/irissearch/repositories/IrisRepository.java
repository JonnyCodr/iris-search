package com.reporting.iris.irissearch.repositories;

import com.reporting.iris.irissearch.models.CustomerStatusCount;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.io.IOException;
import java.util.List;

public interface IrisRepository {

    List<CustomerStatusCount> queryForCustomerStatusCount(final SqlParameterSource params) throws IOException;
}
