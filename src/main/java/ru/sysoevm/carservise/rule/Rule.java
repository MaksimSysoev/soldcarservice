package ru.sysoevm.carservise.rule;

import org.springframework.jdbc.core.JdbcTemplate;

public interface Rule {
    void apply(JdbcTemplate jdbcTemplate);
    boolean validate();
}
