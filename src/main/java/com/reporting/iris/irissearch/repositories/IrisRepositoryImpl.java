package com.reporting.iris.irissearch.repositories;

import com.reporting.iris.irissearch.models.CustomerStatusCount;
import com.reporting.iris.irissearch.utils.ReportUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class IrisRepositoryImpl implements IrisRepository {

    private final NamedParameterJdbcTemplate jdbcTemplateIris;

    public IrisRepositoryImpl(@Qualifier("jdbcTemplateIris") NamedParameterJdbcTemplate jdbcTemplateIris) {
        this.jdbcTemplateIris = jdbcTemplateIris;
    }

    public List<CustomerStatusCount> queryForCustomerStatusCount(final SqlParameterSource params) throws IOException {
        return jdbcTemplateIris.query(ReportUtils.loadQueryFromFile("customer_status_count.sql"), params, new CustomerStatusCountRowMapper());
    }
}

class CustomerStatusCountRowMapper implements RowMapper<CustomerStatusCount> {

    @Override
    public CustomerStatusCount mapRow(ResultSet resultSet, int i) throws SQLException {

        CustomerStatusCount thisRow = new CustomerStatusCount();

        thisRow.setCustomer(resultSet.getString("customer"));
        thisRow.setStatus(resultSet.getString("status"));
        thisRow.setCount(resultSet.getInt("count"));
        return thisRow;
    }
}




