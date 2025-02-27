package com.datn.qlct.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PrefixTrackerService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getNext(String index) {
        String prefix = index;
        Integer currentValue = jdbcTemplate.queryForObject(
                "SELECT current_value FROM prefix_tracker WHERE prefix = ?",
                new Object[]{prefix},
                Integer.class);

        int newValue = currentValue + 1;

        jdbcTemplate.update(
                "UPDATE prefix_tracker SET current_value = ? WHERE prefix = ?",
                newValue, prefix);

        return prefix + newValue;
    }
}
