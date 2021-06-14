package ru.sysoevm.carservise.rule;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.sysoevm.carservise.data.SoldCarServiceDTO;
import ru.sysoevm.carservise.rule.statechange.CarServiceStateEnum;

import java.sql.Date;
import java.util.List;

public interface Rule {
    Date getFrom();
    Date getTo();
    CarServiceStateEnum getState();
    List<SoldCarServiceDTO> handle(JdbcTemplate jdbcTemplate);
}
