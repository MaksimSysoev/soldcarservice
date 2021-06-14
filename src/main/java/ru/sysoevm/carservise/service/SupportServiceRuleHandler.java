package ru.sysoevm.carservise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sysoevm.carservise.data.SoldCarServiceDTO;
import ru.sysoevm.carservise.rule.Rule;
import ru.sysoevm.carservise.rule.statechange.CarServiceStateEnum;

@Component
public class SupportServiceRuleHandler {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void handle(Rule rule) {
        rule.handle(jdbcTemplate).forEach(soldCarServiceDTO -> {
            updateStatus(soldCarServiceDTO, rule.getState());
        });
    }

    private void updateStatus(SoldCarServiceDTO soldCarServiceDTO, CarServiceStateEnum state) {

    }
}
