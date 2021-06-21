package ru.sysoevm.carservise.process;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.sysoevm.carservise.data.SoldCarServiceDTO;
import ru.sysoevm.carservise.rule.*;
import ru.sysoevm.carservise.rule.statechange.*;
import ru.sysoevm.carservise.utils.DateUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class UpdateStateProcess {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Scheduled(cron = "0 52 15 * * *")
    public void start() {
        List<Rule> rules = new ArrayList<>();

        rules.add(new StateChangeRule()
                .setFrom(DateUtils.generate(Calendar.MONTH, -12))
                .setTo(DateUtils.generate(Calendar.DAY_OF_YEAR, -10))
                .setState(CarServiceStateEnum.SERVICE1)
        );

        rules.add(new StateChangeRule()
                .setFrom(DateUtils.generate(Calendar.MONTH, -24))
                .setTo(DateUtils.generate(Calendar.MONTH, -12))
                .setState(CarServiceStateEnum.SERVICE2)
        );

        rules.add(new StateChangeRule()
                .setFrom(DateUtils.generate(Calendar.MONTH, -37))
                .setTo(DateUtils.generate(Calendar.MONTH, -24))
                .setState(CarServiceStateEnum.SERVICE3)
        );

        rules.add(new StateChangeRule()
                .setTo(DateUtils.generate(Calendar.MONTH, -37))
                .setState(CarServiceStateEnum.CLOSED)
        );

        update(rules);
    }


    public void update(List<Rule> rules) {
        for (Rule rule : rules) {
            if (rule.validate()) {
                rule.apply(jdbcTemplate);
            }
        }
    }


}
